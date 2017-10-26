package com.lanou.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    //添加
    public void add(Cost cost) {
        System.out.println(cost);
        cost.setCreatime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
        cost.setStatus("暂停");
        mapper.insertSelective(cost);
    }
    //删除
    public void del(Integer id) {
        if (mapper.selectByPrimaryKey(id).getStatus().equals("开通")){
            return;
        }
//        Cost cost = mapper.selectByPrimaryKey(id);
//        cost.setStatus("删除");
//        mapper.updateByPrimaryKeySelective(cost);
        mapper.deleteByPrimaryKey(id);
    }
    //详情
    public Cost mod(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
    //修改
    public void update(Cost cost) {
        if (cost.getStatus().equals("开通")){
            return;
        }
        mapper.updateByPrimaryKeySelective(cost);
    }

    public Cost selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
    //状态
    public void updateByPrimaryKeySelective(Cost cost) {
        if (cost.getStatus().equals("开通")){
            return;
        }
        if (cost.getStatus().equals("暂停")){
            cost.setStartime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
            cost.setStatus("开通");
            mapper.updateByPrimaryKeySelective(cost);
        }
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
