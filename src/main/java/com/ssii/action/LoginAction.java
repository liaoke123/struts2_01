package com.ssii.action;

import com.ssii.dao.impl.UserDao;
import com.ssii.pojo.AdminInfo;

public class LoginAction extends BaseAction {
    //设置了getter 和 setter方法,是为了获取页面传递过来的参数
    private AdminInfo info;

    public String execute() throws Exception {
        //调用dao查询数据库
        //System.out.println("AdminCode:" + info.getAdminCode());
        //System.out.println("password:"  + info.getPassword());
        UserDao dao = new UserDao();
        info = dao.findByCodeAndPwd("1001_syl", "syl123");
        //判断是否获取到用户信息(是否存在该用户)
        if (info != null) {
           /* ActionContext context = ActionContext.getContext();
            Map<String, Object> session = context.getSession();*/
            //将用户信息存入session中后,返回登录成功的页面
            this.session.put("user",info);
            return "success";
        }else {
            //未能查询到用户信息,跳转到登录页面
            return "login";
        }

    }

    public AdminInfo getInfo() {
        return info;
    }

    public void setInfo(AdminInfo info) {
        this.info = info;
    }
}
