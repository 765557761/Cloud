package com.lanou.cost.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/20.
 */
@Controller
public class CostController {

    @Resource
    private CostService service;
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/toFeeListPage")
    public String costList(){
        return "fee/fee_list";
    }
    @RequestMapping(value = "/feeAdd")
    public String feeAdd(){
        return "fee/fee_add";
    }
    @RequestMapping(value = "/modifyJsp")
    public String modifyJsp(){
        System.out.println("modityJsp");
        return "/fee/fee_modi";
    }
    @RequestMapping(value = "/detailJsp")
    public String detailJsp(){
        System.out.println("detailJsp");
        return "/fee/fee_detail";
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @ResponseBody
    @RequestMapping(value = "/findAll")
    public List<Cost> findAll(){
        System.out.println("findAll");
        return service.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/addFee",method = RequestMethod.POST)
    public String fee_add(Cost cost){
        service.add(cost);
        return "fee/fee_list";
    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String del(@RequestParam("Did") Integer id){
        service.del(id);
        return "fee/fee_list";
    }

    // 修改
    @ResponseBody
    @RequestMapping(value = "/modify1")
    public String modify(@RequestParam("Mid") Integer id,
                         HttpServletRequest request){
        request.getSession().setAttribute("Mid",id);
        Cost mod = service.mod(id);
        request.getSession().setAttribute("mod",mod);
        return "mod";
    }
    @RequestMapping(value = "/modifyNew",method = RequestMethod.POST)
    public String modifyNew(Cost cost,HttpServletRequest request){
        System.out.println(cost);
        Integer mid = (Integer) request.getSession().getAttribute("Mid");
        cost.setCostId(mid);
        service.update(cost);
        return "fee/fee_list";
    }
    @ResponseBody
    @RequestMapping(value = "/mod")
    public Cost mod(HttpServletRequest request){
        System.out.println("Controller---mod");
        return (Cost) request.getSession().getAttribute("mod");
    }

    // 分页
    @ResponseBody
    @RequestMapping(value = "/stu",method = RequestMethod.POST)
    public List<Cost> studentList(@RequestParam("no") Integer pagetNo,
                                     @RequestParam("size") Integer pageSize){
        System.out.println("pageNo:" + pagetNo + "  " + "pageSize:" + pageSize);
        return service.findwithPageInfo(pagetNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo",method = RequestMethod.POST)
    public PageInfo<Cost> pageInfo(@RequestParam("pageSize") Integer pageSize){
        System.out.println("pageInfo");
        return service.getPageInfo(pageSize);
    }

    // 查看
    @ResponseBody
    @RequestMapping(value = "/detail")
    public String detail(@RequestParam("did") Integer id,
                         HttpServletRequest request){
        System.out.println("Did:" + id);
        request.getSession().setAttribute("Did",id);
        return "/fee/fee_detail";
    }
    @ResponseBody
    @RequestMapping(value = "/newDetail")
    public Cost newDetail(HttpServletRequest request){
        Integer did = (Integer) request.getSession().getAttribute("Did");
        System.out.println(did);
        Cost cost = service.selectById(did);
        System.out.println(cost);
        return cost;
    }

    // 启用
    @RequestMapping(value = "/start",method = RequestMethod.POST)
    public void start(@RequestParam("Sid") Integer id){
        Cost cost = service.selectById(id);
        cost.setStatus("1");
        cost.setStartime(new Date());
        service.updateByPrimaryKeySelective(cost);
    }

}
