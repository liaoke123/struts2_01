package com.ssii.dao;

import com.ssii.pojo.Fee;

import java.util.List;

public interface IFeedao {
    public void save(Fee fee);
    public void update(Fee fee);
    public void delete(Fee fee);
    public List<Fee> findAll();
    public Fee findById(int id);
}
