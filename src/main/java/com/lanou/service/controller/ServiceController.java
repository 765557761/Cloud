package com.lanou.service.controller;
import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.service.AccountService;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import com.lanou.service.bean.SService;
import com.lanou.service.service.SerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {
    @Resource
    private SerService service;
    @Resource
    private AccountService accountService;
    @Resource
    private CostService costService;

    @RequestMapping(value = "/toServiceListPage")
    public String toServiceListPage(){
        return "service/service_list";
    }
    @RequestMapping(value = "/toServiceAddPage")
    public String toServiceAddPage(){
        return "service/service_add";
    }
    @RequestMapping(value = "/toServiceDetailPage")
    public String toServiceDetailPage(){
        return "service/service_detail";
    }
    @RequestMapping(value = "/toServiceModiPage")
    public String toServiceModiPage(){
        return "service/service_modi";
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // 分页
    @ResponseBody
    @RequestMapping(value = "/stu3",method = RequestMethod.POST)
    public List<SService> studentList(@RequestParam("no") Integer pagetNo,
                                      @RequestParam("size") Integer pageSize){
        System.out.println("pagetNo:---"+pagetNo + "pageSize:---"+pageSize);
        return service.findwithPageInfo(pagetNo,pageSize);
    }
    @ResponseBody
    @RequestMapping(value = "/pageInfo3",method = RequestMethod.POST)
    public PageInfo<SService> pageInfo(@RequestParam("pageSize") Integer pageSize){
        return service.getPageInfo(pageSize);
    }
    // 修改(回显)
    @ResponseBody
    @RequestMapping(value = "/modifyS")
    public SService modifyS(@RequestParam("Mid") Integer id,
                            HttpServletRequest request){
        request.getSession().setAttribute("SService",service.findById(id));
        return service.findById(id);
    }
    @ResponseBody  // 显示所有资费
    @RequestMapping(value = "/modS1")
    public List<String> modS1(HttpServletRequest request){
        List<Cost> list = costService.findAll();
        List<String> costList = new ArrayList<String>();
        for (Cost cost : list) {
            costList.add(cost.getDescr());
        }
        request.getSession().setAttribute("costTypeList",costList);
        return (List<String>) request.getSession().getAttribute("costTypeList");
    }
    @ResponseBody
    @RequestMapping(value = "/modS")
    public SService modS(HttpServletRequest request){
        return (SService) request.getSession().getAttribute("SService");
    }
    @ResponseBody
    @RequestMapping(value = "/modiSs",method = RequestMethod.POST)
    public String modiS(@RequestParam("descr") String descr,
                        HttpServletRequest request){
        SService sService = (SService) request.getSession().getAttribute("SService");
        Cost cost = costService.selectById(sService.getCostId());
        cost.setDescr(descr);
        costService.updateByPrimaryKeySelective(cost);
        return "modiSOK";
    }
    //添加
    @ResponseBody
    @RequestMapping(value = "/findIdCardAdd",method = RequestMethod.POST)
    public Integer findIdCardAdd(@RequestParam("idCardAdd") String idcard){
        Account account = accountService.findIdCard(idcard);
        return account.getAccountId();
    }
    @ResponseBody
    @RequestMapping(value = "/addServiceS",method = RequestMethod.GET)
    public String addService(SService sService,
                             @RequestParam("descr") String descr,
                             @RequestParam("samePasswd") String samePasswd){
        sService.setStatus("开通");
        Cost cost = costService.selectByDescr(descr);
        sService.setCostId(cost.getCostId());
        sService.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
        service.add(sService,samePasswd);
        return "addServiceOK";
    }
    //状态
    @ResponseBody
    @RequestMapping(value = "/setStateS",method = RequestMethod.POST)
    public String setStateS(@RequestParam("Sid") Integer id){
        service.setStateS(id);
        return "setStateSOK";
    }
    //详情
    @ResponseBody
    @RequestMapping(value = "/detailSS")
    public String detailS(@RequestParam("Did") Integer id,
                          HttpServletRequest request){
//        System.out.println(service.findById(id).getStatus());
        request.getSession().setAttribute("detailSService",service.findById(id));
        return "detailSOK";
    }
    @ResponseBody
    @RequestMapping(value = "/detailSs")
    public SService detailSs(HttpServletRequest request){
        return (SService) request.getSession().getAttribute("detailSService");
    }

}
