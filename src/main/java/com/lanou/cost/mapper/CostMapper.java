package com.lanou.cost.mapper;

import com.lanou.cost.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    void insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    List<Cost> findAll();

    Cost selectByDescr(String descr);
}