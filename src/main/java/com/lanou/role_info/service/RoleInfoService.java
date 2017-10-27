package com.lanou.role_info.service;

import com.github.pagehelper.PageInfo;
import com.lanou.role_info.bean.RoleInfo;
import com.lanou.service.bean.SService;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */

public interface RoleInfoService {
    List<RoleInfo> findwithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<RoleInfo> getPageInfo(Integer pageSize);

    List<RoleInfo> findAll();

    RoleInfo findNameById(Integer id);

    void delById(Integer id);
}
