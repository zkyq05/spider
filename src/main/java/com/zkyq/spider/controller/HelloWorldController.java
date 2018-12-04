package com.zkyq.spider.controller;

import com.zkyq.spider.NewsRepository;
import com.zkyq.spider.UserService;
import com.zkyq.spider.modle.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/getUser")
    public List<News> getUser() {
        List<News> page=userService.getNewsList();
        return page;
    }
}
