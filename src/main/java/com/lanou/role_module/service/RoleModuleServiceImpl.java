package com.lanou.role_module.service;

import com.lanou.role_module.bean.RoleModule;
import com.lanou.role_module.mapper.RoleModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/10/30.
 */
@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Resource
    private RoleModuleMapper mapper;


    public void update(Integer roleId, Integer moduleId) {
        RoleModule roleModule = new RoleModule();
        roleModule.setModuleId(moduleId);
        roleModule.setRoleId(roleId);
        mapper.insertSelective(roleModule);
    }

    public void insert(Integer roleId, Integer moduleId) {
        RoleModule roleModule = new RoleModule();
        roleModule.setRoleId(roleId);
        roleModule.setModuleId(moduleId);
        mapper.insertSelective(roleModule);
    }
}
