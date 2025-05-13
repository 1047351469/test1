package com.anyi.reggie.controller;

import test.UserConfig;
import com.anyi.reggie.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserConfig userConfig;

    @Autowired
    private Dish dish;
   @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/getUserConfig")
    public UserConfig getUserConfig(@RequestBody UserConfig u){
        System.out.println(dish);
        System.out.println(u);
        if(false){
            throw new RuntimeException();
        }
        System.out.println(userConfig);
        return userConfig;
    }



}
