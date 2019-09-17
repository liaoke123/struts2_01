package com.ssii.com.ssii.test;

import com.ssii.service.FeeServiceSimple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试面向切面编程
public class TestAop {
    public static void main(String[] args) {
        //根据spring的配置文件初始化容器
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取bean
        FeeServiceSimple feeService = (FeeServiceSimple) ac.getBean("feeService");
        //调用bean的方法
        feeService.findAll();
    }
}
