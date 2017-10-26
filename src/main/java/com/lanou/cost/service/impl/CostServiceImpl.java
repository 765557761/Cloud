package com.lanou.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/21.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper mapper;

    public List<Cost> findAll() {
        return mapper.findAll();
    }

    public void add(Cost cost) {
        mapper.insertSelective(cost);
    }

    public void del(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public Cost mod(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(Cost cost) {
        mapper.updateByPrimaryKeySelective(cost);
    }

    public Cost selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(Cost cost) {
        mapper.updateByPrimaryKeySelective(cost);
    }

    public Cost selectByDescr(String descr) {
        return mapper.selectByDescr(descr);
    }


    public List<Cost> findwithPageInfo(Integer pageNo, Integer pageSize) {
        // 目标：获取PageInfo对象
        PageInfo<Cost> pageInfo = queryStudentByPage(pageNo,pageSize);
        return pageInfo.getList();
    }
    // 获取分页信息
    public PageInfo<Cost> getPageInfo(Integer pageSize) {
        return queryStudentByPage(null,pageSize);
    }
    public PageInfo<Cost> queryStudentByPage(Integer pageNo, Integer pageSize){
        // 判断参数的合法性
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 6 : pageSize;
        PageHelper.startPage(pageNo,pageSize);
        // 获取全部学员
        List<Cost> costList = mapper.findAll();
        System.out.println(costList);
        // 使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);
//        System.out.println(pageInfo);
        return pageInfo;
    }
}
