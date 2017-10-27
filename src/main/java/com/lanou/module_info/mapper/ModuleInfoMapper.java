package com.lanou.module_info.mapper;

import com.lanou.module_info.bean.ModuleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleInfoMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(ModuleInfo record);

    int insertSelective(ModuleInfo record);

    List<ModuleInfo> selectByPrimaryKey(@Param("role_id") Integer id);

    int updateByPrimaryKeySelective(ModuleInfo record);

    int updateByPrimaryKey(ModuleInfo record);
}