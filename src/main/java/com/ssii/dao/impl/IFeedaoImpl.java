package com.ssii.dao.impl;

import com.ssii.dao.IFeedao;
import com.ssii.pojo.Fee;
import com.ssii.utils.FeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class IFeedaoImpl extends JdbcDaoSupport implements IFeedao {
    private static final String saveStr = "insert into cost(id,name,base_duration,base_cost)" +
            "values(?,?,?,?)";
    private static final String updateStr = "update cost set id=?,name=?,base_duration=?,base_cost=?";
    private static final String deleteStr = "delete from cost where id=?";
    private static final String findAll = "select * from cost";
    private static final String find2 = "select * from cost where id=?";

    @Override
    public void save(Fee fee) {
        Object[] params = {fee.getId(),fee.getFeeName(),fee.getBaseDuration(),fee.getBaseCost()};
        this.getJdbcTemplate().update(saveStr,params);
    }

    @Override
    public void update(Fee fee) {
        Object[] params = {fee.getId(),fee.getFeeName(),fee.getBaseDuration(),fee.getBaseCost()};
        this.getJdbcTemplate().update(updateStr,params);
    }

    @Override
    public void delete(Fee fee) {
        Integer id = fee.getId();
        this.getJdbcTemplate().update(deleteStr,id);
    }

    @Override
    public List<Fee> findAll() {
        FeeMapper feeMapper = new FeeMapper();
        List<Fee> list = this.getJdbcTemplate().query(findAll, feeMapper);
        return list;
    }

    @Override
    public Fee findById(int id) {
        FeeMapper feeMapper = new FeeMapper();
        Fee fee = (Fee) this.getJdbcTemplate().queryForObject(find2, feeMapper, id);
        return fee;
    }

}
