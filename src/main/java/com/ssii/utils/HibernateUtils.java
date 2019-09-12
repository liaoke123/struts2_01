package com.ssii.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 用户获取会话session和关闭session
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;
    static {
        try {
            //加载hibernate的全局配置文件(hibernate.cfg.xml)
            Configuration configuration = new Configuration().configure();

            //会话工厂
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    //获取会话
    public static Session getSession(){
        Session session = sessionFactory.openSession();
        return session;
    }

    //关闭会话
    public static void closeSession(Session session){
        if (session != null) {
            if(session.isOpen()){
                session.close();
            }
        }
    }

    //获取会话工厂
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }



}
