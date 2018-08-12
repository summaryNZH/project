package com.project.demo.controller;

import com.project.demo.bean.AppMessage;
import com.project.demo.service.AppMessageService;
import com.project.demo.utils.api.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by NingZhenHang 2018/8/12.
 */
@RestController
@RequestMapping("/app")
@Slf4j
public class APPMessageController {

    @Resource
    private AppMessageService service;

    @GetMapping("/query/v1")
    public List<AppMessage> query() {

        List<AppMessage> list = service.getAllMessage();
        log.info("list :{}", JsonUtil.toJson(list));
        return list;
    }


}