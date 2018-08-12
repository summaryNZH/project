package com.project.demo.mapper;

import com.project.demo.bean.AppMessage;

import java.util.List;

/**
 * Created by NingZhenHang 2018/8/12.
 */

public interface AppMessageMapper {

    List<AppMessage> selectAll();
}
