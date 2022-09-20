package com.shudong.treehole.controller;

import com.shudong.treehole.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: treehole
 * @description: 测试控制类
 * @author: 王珺玉
 * @create: 2022-06-05 10:48
 **/

@RestController
@RequestMapping("v1/test")
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/hello")
    public String sayHello(){
        return testService.testHello();
    }


}
