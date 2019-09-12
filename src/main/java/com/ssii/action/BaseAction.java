package com.ssii.action;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class BaseAction implements SessionAware {
    protected Map<String,Object> session;
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
