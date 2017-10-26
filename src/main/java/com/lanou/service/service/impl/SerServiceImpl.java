package com.lanou.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.service.mapper.ServiceMapper;
import com.lanou.service.bean.SService;
import com.lanou.service.service.SerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Service
public class SerServiceImpl implements SerService {
    @Resource
    private ServiceMapper mapper;

    public List<SService> findwithPageInfo(Integer pageNo, Integer pageSize) {
        // 目标：获取PageInfo对象
        PageInfo<SService> pageInfo = queryStudentByPage(pageNo, pageSize);
        return pageInfo.getList();
    }
    // 获取分页信息
    public PageInfo<SService> getPageInfo(Integer pageSize) {
        return queryStudentByPage(null, pageSize);
    }

    public SService findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void modi(SService sService) {
        mapper.updateByPrimaryKeySelective(sService);
    }

    public void add(SService sService, String samePasswd) {
        if (sService.getLoginPasswd().equals(samePasswd)){
            System.out.println("SServiceImpl");
            mapper.insertSelective(sService);
        }
        return;
    }

    public void setStateS(Integer id) {
        SService sService = mapper.selectByPrimaryKey(id);
        if (sService.getStatus().equals("0")){
            mapper.setState1(id);
        }
        if (sService.getStatus().equals("1")){
            mapper.setState0(id);
        }
    }

    public PageInfo<SService> queryStudentByPage(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 6 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        // 获取全部学员
        List<SService> serviceList = mapper.findAll();
        System.out.println(serviceList);
        // 使用PageInfo对结果进行包装
        PageInfo<SService> pageInfo = new PageInfo<SService>(serviceList);
        return pageInfo;
    }
}
