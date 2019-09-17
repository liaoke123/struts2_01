package com.ssii.advice;

import org.apache.log4j.Logger;

//通知 将作用到某一批service上
public class LoggerAdvice {
    public void execute(Exception e){
        System.out.println("=======记录异常信息========");
        //获取异常记录
        StackTraceElement[] stackTrace = e.getStackTrace();
        //获取日志记录对象
        Logger logger = Logger.getLogger(this.getClass());
        //输出内容
        logger.error(e.getClass().getName());
        //利用log4j输出记录
        logger.error(stackTrace[0]);
        logger.warn("这里是log4j输出的warn内容");
        logger.info("这里是log4j输出的info内容");
        logger.debug("这里是log4j输出的debug内容");
        System.out.println("增强结束");
    }

}
