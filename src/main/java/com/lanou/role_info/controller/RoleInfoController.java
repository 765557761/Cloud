package com.lanou.role_info.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.github.pagehelper.PageInfo;
import com.lanou.role_info.bean.RoleInfo;
import com.lanou.role_info.service.RoleInfoService;
import com.lanou.service.bean.SService;
import com.oracle.tools.packager.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.ch.IOUtil;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Controller
public class RoleInfoController {
    @Resource
    private RoleInfoService service;

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
    @ResponseBody
    @RequestMapping(value = "/modiRM")
    public String modiRM(@RequestParam("nameRM") String name,
                         @RequestParam("arr") Byte arr){
        System.out.println("arr:" + arr);
        return "modiOK";
    }
    //删除
    @ResponseBody
    @RequestMapping(value = "/delRM")
    public String delRM(@RequestParam("RMDid") Integer id){
        service.delById(id);
        return "delOK";
    }
    //添加
    @ResponseBody
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRM(
                        @RequestParam("name") String name) throws Exception {
        System.out.println(name);
        return "addRMOKOKOK";
    }

}
