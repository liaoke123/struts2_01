package com.ssii.action;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
/*
这里之所以是实现而不是继承,是为了以后在更换框架的时候更加的灵活
测试版本1.0 的后修改的注释
 */
public class BaseAction implements SessionAware {
    protected Map<String,Object> session;
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
