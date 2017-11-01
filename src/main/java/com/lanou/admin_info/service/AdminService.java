package com.lanou.admin_info.service;


import com.github.pagehelper.PageInfo;
import com.lanou.admin_info.bean.AdminInfo;

/**
 * Created by dllo on 17/10/28.
 */
public interface AdminService {
    PageInfo<AdminInfo> findAllAdmin(Integer pageNo, Integer pageSize);

    boolean deleteTheAdmin(Integer adminId);
}
