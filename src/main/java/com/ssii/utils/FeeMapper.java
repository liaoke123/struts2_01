package com.ssii.utils;

import com.ssii.pojo.Fee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
完成结果集中的字段值与Fee属性之间的映射关系
*/
public class FeeMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Fee fee = new Fee();
        fee.setId(resultSet.getInt("id"));
        fee.setFeeName(resultSet.getString("name"));
        fee.setBaseDuration(resultSet.getInt("base_duration"));
        fee.setBaseCost(resultSet.getFloat("base_cost"));
        return fee;
    }
}
