package com;

import com.anyi.reggie.config.DynamicImportSelector;
import com.anyi.reggie.entity.Dish;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import test.UserConfig;

import java.util.Date;

/**
 * @author 安逸i
 * @version 1.0
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = "com.anyi.reggie.mapper")
@Import({DynamicImportSelector.class})
public class ReggieApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReggieApplication.class, args);
        Dish dish = context.getBean(Dish.class);
        System.out.println(dish);
    }
   @Bean
    public Dish getDish(){
        Dish dish=new Dish();
        dish.setCategoryId(1L);
        dish.setCode("11");
        dish.setCreateTime(new Date());
        dish.setDescription("11");
        dish.setCreateUser(1L);
        dish.setName("1");
        return dish;
   }
}
