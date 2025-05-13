package com.anyi.reggie.controller;


import com.anyi.reggie.common.CommentRedis;
import com.anyi.reggie.common.R;
import com.anyi.reggie.entity.Category;
import com.anyi.reggie.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author anyi
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R page(Integer page,Integer pageSize){
        Page<Category> categoryPage = new Page<>();
        QueryWrapper<Category> wr = new QueryWrapper<>();
        wr.orderByDesc("sort");
        categoryService.page(categoryPage, wr);
        return R.success(categoryPage);
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @PostMapping
    public R addCate(@RequestBody Category category){
        categoryService.save(category);
        return R.success("添加成功");
    }
    /**
     * 删除分类
     */
    @DeleteMapping
    public R delete( Long ids){

        categoryService.delete(ids);
        return R.success("删除成功");

    }
    /**
     * 更新分类
     */
    @PutMapping
    public R update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("更新成功");
    }

    /**
     * 获取所有菜品分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    public R list(Integer type){
//        return R.error("错了");
        String key = CommentRedis.CATEGORY_PREFIX + type;

        List<Category> categories = (List<Category>) redisTemplate.opsForValue().get(key);
        if (categories !=null){
            return R.success(categories);
        }
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if(type !=null){
            wrapper.eq("type", type);
        }
        List<Category> list = categoryService.list(wrapper);
        redisTemplate.opsForValue().set(key, list,30, TimeUnit.MINUTES);

        return R.success(list);
    }

    @PostMapping("/fullRequestInfo")
    public String getFullRequestInfo(HttpServletRequest request) throws Exception {
        StringBuilder result = new StringBuilder();

        // 获取请求方法和 URI
        result.append("Method: ").append(request.getMethod()).append("\n");
        result.append("Request URI: ").append(request.getRequestURI()).append("\n");

        // 获取所有请求头
        result.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            result.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }

        // 获取请求参数
        result.append("Parameters:\n");
        request.getParameterMap().forEach((key, value) -> {
            result.append(key).append(": ").append(String.join(", ", value)).append("\n");
        });

        // 获取请求体
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }
        result.append("Body:\n").append(body);
        System.out.println(result.toString());
        return result.toString();
    }
}