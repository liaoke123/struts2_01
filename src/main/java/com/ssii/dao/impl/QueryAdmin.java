package com.ssii.dao.impl;

import com.ssii.pojo.AdminInfo;
import com.ssii.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

//使用hibernate的方式进行查询
public class QueryAdmin {
    //设置查询语句
    String sql = "from AdminInfo where adminCode=?";
    public void test1() {
        //获取session
        Session session = HibernateUtils.getSession();
        //根据session获取查询对象
        Query query = session.createQuery(sql);
        //开启查询缓存
        query.setCacheable(true);
        //为占位符设置值
        query.setParameter(0, "admin");
        //进行查询
        List<AdminInfo> list = query.list();
        //对查询结果进行判断
        if (!list.isEmpty()) {
            AdminInfo adminInfo = list.get(0);
            System.out.println(adminInfo.getAdminCode() + adminInfo.getEmail());
        }
        HibernateUtils.closeSession(session);
    }

    /*
    将查询条件封装成对象,获取到的查询结果也是对象
     */
    public void test2(){
        //设置查询语句
        String sql = "select new AdminInfo(a.name,a.telephone,a.email) from AdminInfo a where a.adminCode=?";
        //获取hibernate中的session对象
        Session session = HibernateUtils.getSession();
        //获取查询对象
        Query query = session.createQuery(sql);
        //设置占位符的参数
        query.setParameter(0,"admin");
        //执行查询
        AdminInfo adminInfo = (AdminInfo) query.uniqueResult();
        //处理结果
        System.out.println(adminInfo.getAdminCode() + adminInfo.getPassword());
        //关闭session
        HibernateUtils.closeSession(session);
    }

    /*
    将查询语句放到映射文件中
     */
    public void test3(){
        //获取session
        Session session = HibernateUtils.getSession();
        //根据映射文件中的语句生成查询对象
        Query query = session.getNamedQuery("findAdmin");
        //设置占位符的值
        query.setParameter(0,"admin");
        //执行查询
        AdminInfo adminInfo = (AdminInfo) query.uniqueResult();
        //处理结果
        System.out.println(adminInfo.getAdminCode() + adminInfo.getEmail());
        //关闭session
        HibernateUtils.closeSession(session);

    }

    /*
    分页查询
     */
    public void test4(){
        Session session = HibernateUtils.getSession();
        String hql = "from AdminInfo";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(8);
        List<AdminInfo> list = query.list();
        for (AdminInfo adminInfo : list) {
            System.out.println(adminInfo.getAdminCode() + adminInfo.getEmail());
        }
        HibernateUtils.closeSession(session);
    }

    /*
    测试criteria查询
     */
    public void test5(){
        Session session = HibernateUtils.getSession();
        Criteria criteria = session.createCriteria(AdminInfo.class);
        String sql = "select * from admin_info";
        NativeQuery sqlQuery = session.createSQLQuery(sql);

        criteria.add(Restrictions.and(Restrictions.eq("admincode",99),Restrictions.eq("telephone","13551044545")));
        criteria.addOrder(Order.desc("id"));
        List<AdminInfo> list = criteria.list();
        //处理结果

        //关闭session
        HibernateUtils.closeSession(session);

    }

    //criteria创建方法
    public void test6(){
        //获取session
        Session session = HibernateUtils.getSession();
        //获取criteria的builder
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //获取criteria条件
        CriteriaQuery<AdminInfo> cq = criteriaBuilder.createQuery(AdminInfo.class);
        //指定查询的条件
       cq.from(AdminInfo.class);
        //创建查询对象
        Query<AdminInfo> query = session.createQuery(cq);
        //执行查询
        List<AdminInfo> resultList = query.getResultList();
        //处理结果
        for (AdminInfo adminInfo : resultList) {
            System.out.println(adminInfo.getAdminCode() + adminInfo.getEmail());
        }

    }

    public void test7(){
        //获取session
        Session session = HibernateUtils.getSession();
        //通过session创建criteriaBuilder对象
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //创建criteriaQuery对象
        CriteriaQuery<AdminInfo> cq = criteriaBuilder.createQuery(AdminInfo.class);
        //指定查询条件
        Root<AdminInfo> root = cq.from(AdminInfo.class);
        cq.where(criteriaBuilder.equal(root.get("adminCode"),"list"));
        //创建查询对象
        Query<AdminInfo> query = session.createQuery(cq);
        //执行查询
        List<AdminInfo> resultList = query.getResultList();
        //处理结果
        for (AdminInfo o : resultList) {
            System.out.println(o.getAdminCode() + o.getEmail());
        }
        //关闭资源
        HibernateUtils.closeSession(session);
    }
    /*
    hibernate的sql查询
     */
    public void test8(){
        String sql = "select * from admin_info";
        Session session = HibernateUtils.getSession();
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(AdminInfo.class);
        //lockMode 锁模式(悲观锁和乐观锁)
        //sqlQuery.addEntity("","", LockMode.UPGRADE_NOWAIT);
        List<AdminInfo> list = sqlQuery.list();
        for (AdminInfo adminInfo : list) {
            System.out.println(adminInfo.getAdminCode() + adminInfo.getEmail());
        }
        HibernateUtils.closeSession(session);
    }

}
