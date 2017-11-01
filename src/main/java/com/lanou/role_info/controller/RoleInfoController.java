package com.lanou.role_info.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.module_info.bean.ModuleInfo;
import com.lanou.role_info.bean.RoleInfo;
import com.lanou.role_info.service.RoleInfoService;
import com.lanou.role_module.bean.RoleModule;
import com.lanou.role_module.service.RoleModuleService;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleInfoController {
    @Resource
    private RoleInfoService service;
    @Resource
    private RoleModuleService moduleService;

    @RequestMapping(value = "/toRoleInfoListPage")
    public String toRoleInfoListPage(){
        return "role/role_list";
    }
    @RequestMapping(value = "/toRoleInfoAddPage")
    public String toRoleInfoAddPage(){
        return "role/role_add";
    }
    @RequestMapping(value = "/toRoleInfoModiPage")
    public String toRoleInfomodiPage(){
        return "role/role_modi";
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // 分页
    @ResponseBody
    @RequestMapping(value = "/stu4",method = RequestMethod.POST)
    public List<RoleInfo> studentList(@RequestParam("no") Integer pagetNo,
                                      @RequestParam("size") Integer pageSize){
        System.out.println("pagetNo:---"+pagetNo + "pageSize:---"+pageSize);
        return service.findwithPageInfo(pagetNo,pageSize);
    }
    @ResponseBody
    @RequestMapping(value = "/pageInfo4",method = RequestMethod.POST)
    public PageInfo<RoleInfo> pageInfo(@RequestParam("pageSize") Integer pageSize){
//        List<RoleInfo> list = service.findAll();
//        for (RoleInfo roleInfo : list) {
//            System.out.println(roleInfo.getName());
//        }
        service.getPageInfo(pageSize);
        return service.getPageInfo(pageSize);
    }
    //修改
    @ResponseBody
    @RequestMapping(value = "/modiRML")
    public String modiRML(@RequestParam("RMid") Integer id, HttpServletRequest request){
        RoleInfo roleInfo = service.findNameById(id);
        request.getSession().setAttribute("roleInfo",roleInfo);
        return "modiRML";
    }
    @ResponseBody
    @RequestMapping(value = "/roleModi")
    public RoleInfo roleModi(HttpServletRequest request){
        RoleInfo roleInfo = (RoleInfo) request.getSession().getAttribute("roleInfo");
        return roleInfo;
    }
    //删除
    @ResponseBody
    @RequestMapping(value = "/delRM")
    public String delRM(@RequestParam("RMDid") Integer id){
        service.delById(id);
        return "delOK";
    }
    //修改
    @ResponseBody
    @RequestMapping(value = "/modiRM")
    public String modiRM(@RequestParam(value = "nameRM",required = false) String name,
                         @RequestParam(value = "str",required = false) String str,
                         HttpServletRequest request){
        String[] split = str.split(",");
        List<ModuleInfo> moduleInfoList = new ArrayList<ModuleInfo>();
        for (String s : split) {
            ModuleInfo moduleInfo = new ModuleInfo();
            moduleInfo.setModuleId(Integer.valueOf(s));
            moduleInfoList.add(moduleInfo);
        }
        RoleInfo roleInfo = (RoleInfo) request.getSession().getAttribute("roleInfo");
        RoleInfo roleInfo1 = new RoleInfo();
        roleInfo1.setRoleId(roleInfo.getRoleId());
        roleInfo1.setName(name);
        roleInfo1.setModuleInfoList(moduleInfoList);
        service.update(roleInfo1);
        return "modiOK";
    }
    //添加
    @ResponseBody
    @RequestMapping(value = "/addRM")
    public String addRM(@RequestParam(value = "strAdd",required = false) String str,
                        @RequestParam(value = "nameRM",required = false) String name) {
        String[] split = str.split(",");
        List<ModuleInfo> moduleInfos = new ArrayList<ModuleInfo>();
        for (String s : split) {
            ModuleInfo moduleInfo = new ModuleInfo();
            moduleInfo.setModuleId(Integer.valueOf(s));
            moduleInfos.add(moduleInfo);
        }
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setName(name);
        roleInfo.setModuleInfoList(moduleInfos);
        Random random = new Random();
        roleInfo.setRoleId(random.nextInt(1111-1)+800);
        service.insert(roleInfo);
        return "addOK";
    }

}
