package com.project.demo.service;

import com.project.demo.bean.AppMessage;
import com.project.demo.mapper.AppMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by NingZhenHang 2018/8/12.
 */
@Service
public class AppMessageService {

    @Resource
    private AppMessageMapper mapper;

    public List<AppMessage> getAllMessage() {
        return mapper.selectAll();
    }
}