package com.lanou.role_info.mapper;

import com.lanou.role_info.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(@Param("name") String name,@Param("roleId") Integer roleId);

    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> findAllR();

    RoleInfo findRoleByName(String name);

    void insertModule(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);
}