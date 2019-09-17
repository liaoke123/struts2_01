package com.ssii.service;

import com.ssii.dao.impl.FeeDao;
import com.ssii.pojo.Fee;

import java.util.List;

public class FeeServiceSimple {
    //依赖注入(构造方法注入与setter方法注入中的setter方法注入)
    private  FeeDao feeDao;
    public void setFeeDao(FeeDao feeDao) {
    }

    //调用dao查询所有
    public void findAll(){
        List<Fee> listFeee = feeDao.findAll();
        //处理获取到的结果

        //故意生成一个异常,看log4j记录日志的效果.
        //int i = 1/0;

    }

}
