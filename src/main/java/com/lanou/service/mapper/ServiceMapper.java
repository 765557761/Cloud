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

    void setState1(Integer id);

    void setState0(Integer id);

    List<SService> findS(String osUsername, String unixHost, String status, Integer accountId);
}