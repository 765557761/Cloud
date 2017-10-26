package com.lanou.cost.service;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;

import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
public interface CostService {

    List<Cost> findAll();

    void add(Cost cost);

    void del(Integer id);

    Cost mod(Integer id);

    void update(Cost cost);

    List<Cost> findwithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<Cost> getPageInfo(Integer pageSize);

    Cost selectById(Integer id);

    void updateByPrimaryKeySelective(Cost cost);

    Cost selectByDescr(String descr);
}
