package com.ssii.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssii.dao.impl.FeeDao;
import com.ssii.pojo.Fee;

import java.util.List;

//接受用户请求,返回逻辑视图
public class FeeListAction extends ActionSupport {
    //定义一个封装查询到的数据的对象,便于页面获取数据
    List<Fee> list = null;
    public List<Fee> getList() {
        return list;
    }
    public void setList(List<Fee> list) {
        this.list = list;
    }

    @Override
    public String execute() throws Exception {
        FeeDao feeDao = new FeeDao();
        list = FeeDao.findAll();
        return "success";
    }

}
