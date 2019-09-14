package com.ssii.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "admin_info", schema = "test", catalog = "")
public class AdminInfo {
    private int id;
    private String adminCode;
    private String password;
    private String name;
    private String telephone;
    private String email;
    private Date erolldate;

    //便于处理数据库查询结果时,快速的将结果转换成对象.
    public static AdminInfo toAdminInfo(ResultSet rs) throws SQLException {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(rs.getInt(1));
        adminInfo.setAdminCode(rs.getString(2));
        adminInfo.setPassword(rs.getString(3));
        adminInfo.setName(rs.getString(4));
        adminInfo.setTelephone(rs.getString(5));
        adminInfo.setEmail(rs.getString(6));
        adminInfo.setErolldate(rs.getDate(7));
        return adminInfo;
    }

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "admin_code", nullable = false, length = 30)
    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 8)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 15)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "erolldate", nullable = false)
    public Date getErolldate() {
        return erolldate;
    }

    public void setErolldate(Date erolldate) {
        this.erolldate = erolldate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminInfo that = (AdminInfo) o;

        if (id != that.id) return false;
        if (adminCode != null ? !adminCode.equals(that.adminCode) : that.adminCode != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (erolldate != null ? !erolldate.equals(that.erolldate) : that.erolldate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (adminCode != null ? adminCode.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (erolldate != null ? erolldate.hashCode() : 0);
        return result;
    }
}
