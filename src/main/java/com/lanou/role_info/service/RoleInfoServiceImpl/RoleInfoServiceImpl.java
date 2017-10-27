package com.lanou.role_info.service.RoleInfoServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.role_info.bean.RoleInfo;
import com.lanou.role_info.mapper.RoleInfoMapper;
import com.lanou.role_info.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper mapper;

    public List<RoleInfo> findwithPageInfo(Integer pageNo, Integer pageSize) {
        // 目标：获取PageInfo对象
        PageInfo<RoleInfo> pageInfo = queryStudentByPage(pageNo, pageSize);
        return pageInfo.getList();
    }
    // 获取分页信息
    public PageInfo<RoleInfo> getPageInfo(Integer pageSize) {
        return queryStudentByPage(null, pageSize);
    }

    public List<RoleInfo> findAll() {
        return mapper.findAllR();
    }

    public RoleInfo findNameById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void delById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public PageInfo<RoleInfo> queryStudentByPage(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 6 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        // 获取全部学员
        List<RoleInfo> roleInfoList = mapper.findAllR();
        // 使用PageInfo对结果进行包装
        PageInfo<RoleInfo> pageInfo = new PageInfo<RoleInfo>(roleInfoList);
        return pageInfo;
    }
}
