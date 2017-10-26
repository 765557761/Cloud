package com.lanou.service.mapper;

import com.lanou.service.bean.SService;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(SService record);

    void insertSelective(SService record);

    SService selectByPrimaryKey(Integer serviceId);

    void updateByPrimaryKeySelective(SService record);

    int updateByPrimaryKey(SService record);

    List<SService> findAll();

}