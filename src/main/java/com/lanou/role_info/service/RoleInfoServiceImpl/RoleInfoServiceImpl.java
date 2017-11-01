package com.lanou.role_info.service.RoleInfoServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.module_info.bean.ModuleInfo;
import com.lanou.role_info.bean.RoleInfo;
import com.lanou.role_info.mapper.RoleInfoMapper;
import com.lanou.role_info.service.RoleInfoService;
import com.lanou.role_module.bean.RoleModule;
import com.lanou.role_module.service.RoleModuleService;
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
    @Resource
    private RoleModuleService service;

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

    public RoleInfo findRoleByName(String name) {
        return mapper.findRoleByName(name);
    }

    public void update(RoleInfo roleInfo1) {
        mapper.updateByPrimaryKeySelective(roleInfo1.getName(),roleInfo1.getRoleId());
        mapper.deleteByPrimaryKey(roleInfo1.getRoleId());
        List<ModuleInfo> moduleInfoList = roleInfo1.getModuleInfoList();
        for (ModuleInfo moduleInfo : moduleInfoList) {
            mapper.insertModule(roleInfo1.getRoleId(),moduleInfo.getModuleId());
        }
    }

    public void insert(RoleInfo roleInfo) {
        mapper.insertSelective(roleInfo);
        List<ModuleInfo> moduleInfoList = roleInfo.getModuleInfoList();
        for (ModuleInfo moduleInfo : moduleInfoList) {
            service.insert(roleInfo.getRoleId(),moduleInfo.getModuleId());
        }
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
