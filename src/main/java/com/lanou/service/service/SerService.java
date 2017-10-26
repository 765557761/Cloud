package com.lanou.service.service;

import com.github.pagehelper.PageInfo;
import com.lanou.service.bean.SService;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface SerService {

    List<SService> findwithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<SService> getPageInfo(Integer pageSize);

    SService findById(Integer id);

    void modi(SService sService);

    void add(SService sService, String samePasswd);

    void setStateS(Integer id);
}
