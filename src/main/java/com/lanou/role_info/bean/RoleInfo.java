package com.lanou.role_info.bean;


import com.lanou.module_info.bean.ModuleInfo;

import java.util.List;

public class RoleInfo {

    private List<ModuleInfo> moduleInfoList;

    private Integer roleId;

    private String name;

    public List<ModuleInfo> getModuleInfoList() {
        return moduleInfoList;
    }

    public void setModuleInfoList(List<ModuleInfo> moduleInfoList) {
        this.moduleInfoList = moduleInfoList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}