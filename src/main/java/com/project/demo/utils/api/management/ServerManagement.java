package com.project.demo.utils.api.management;

public interface ServerManagement {

    void addServiceRecycle(ServiceRecycle recycle);

    boolean healthCheck();

    /**
     * 添加功能的上下线开关
     *
     * @param key
     * @param switchable
     */
    void addSwitcher(String key, Switchable switchable);

    /**
     * 添加功能的健康检查
     *
     * @param key
     * @param checker
     */
    void addHealthChecker(String key, HealthChecker checker);

}
