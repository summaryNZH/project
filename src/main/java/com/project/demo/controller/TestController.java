package com.project.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by NingZhenHang 2018/8/12.
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/hello")
    public String getUserByGet() {
        log.info("收到请求");
        return "Hello ";
    }
}