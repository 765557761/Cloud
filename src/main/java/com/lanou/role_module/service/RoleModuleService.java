package com.lanou.role_module.service;

import com.lanou.role_module.bean.RoleModule;

/**
 * Created by dllo on 17/10/30.
 */
public interface RoleModuleService {


    void update(Integer roleId,Integer moduleId);

    void insert(Integer roleId, Integer moduleId);
}
