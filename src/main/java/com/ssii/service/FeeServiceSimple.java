package com.ssii.service;

import com.ssii.dao.FeeDao;

public class FeeServiceSimple {
    //依赖注入(构造方法注入与setter方法注入中的setter方法注入)
    private  FeeDao feeDao;
    public void setFeeDao(FeeDao feeDao) {
    }

}
