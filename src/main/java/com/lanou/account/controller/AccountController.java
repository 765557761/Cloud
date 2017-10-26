package com.lanou.account.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.exception.AddException;
import com.lanou.account.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class AccountController {
    @Resource
    private AccountService service;

    @RequestMapping(value = "/toAccountListPage")
    public String toAccountListPage(){
        return "account/account_list";
    }
    @RequestMapping(value = "/toAddJsp")
    public String toAddJsp(){
        return "account/account_add";
    }
    @RequestMapping(value = "/toDetailJsp")
    public String toDetailJsp(){
        return "account/account_detail";
    }
    @RequestMapping(value = "/toModiJsp")
    public String toModiJsp(){
        return "account/account_modi";
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Account account,
                      @RequestParam("samePsw") String psw) throws Exception{
        System.out.println(account);
        System.out.println(psw);
        try {
            service.add(account,psw);
        } catch (AddException e) {
            System.out.println(e.getMessage());
        }
        return "添加成功";
    }

    // 分页
    @ResponseBody
    @RequestMapping(value = "/stu2",method = RequestMethod.POST)
    public List<Account> studentList(@RequestParam("no") Integer pagetNo,
                                  @RequestParam("size") Integer pageSize){
//        System.out.println("pageNo:" + pagetNo + "  " + "pageSize:" + pageSize);
        return service.findwithPageInfo(pagetNo,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/pageInfo2",method = RequestMethod.POST)
    public PageInfo<Account> pageInfo(@RequestParam("pageSize") Integer pageSize){
//        System.out.println("pageInfo");
//        service.findAllAccount();
        return service.getPageInfo(pageSize);
    }

    // 查看
    @ResponseBody
    @RequestMapping(value = "/detailAcc")
    public String detailAcc(@RequestParam("Aid") Integer id,
                            HttpServletRequest request){
        Account account = service.selectById(id);
        request.getSession().setAttribute("account",account);
        return "selectOK";
    }
    @ResponseBody
    @RequestMapping(value = "/det")
    public Account det(HttpServletRequest request){
        return (Account) request.getSession().getAttribute("account");
    }

    // 修改
    @ResponseBody
    @RequestMapping(value = "/modifyAcc",method = RequestMethod.POST)
    public String modifyAcc(@RequestParam("Mid") Integer id,
                             HttpServletRequest request){
        Account account = service.selectById(id);
        request.getSession().setAttribute("oldAcc",account);
        return "selectOK";
    }
    @ResponseBody
    @RequestMapping(value = "/modd")
    public Account modd(HttpServletRequest request){
        return (Account) request.getSession().getAttribute("oldAcc");
    }
    @ResponseBody
    @RequestMapping(value = "/modAccount",method = RequestMethod.POST)
    public String modAccount(Account account){
        service.update(account);
        return "updateOK";
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delAcc",method = RequestMethod.POST)
    public String delAcc(@RequestParam("Did") Integer id){
        service.delById(id);
        return "delOK";
    }

    // 暂停
    @ResponseBody
    @RequestMapping(value = "/setState")
    public String setState(@RequestParam("Sid") Integer id){
        Account account = service.selectById(id);
        String status = account.getStatus();
        service.setState(id,account);

        return "setStateOK";
    }

    // 搜索
    @ResponseBody
    @RequestMapping(value = "/like")
    public List<Account> like(Account account){
        String idcardNo = account.getIdcardNo();
        String realName = account.getRealName();
        String loginName = account.getLoginName();
        System.out.println(idcardNo);
        List<Account> list = service.like(idcardNo,realName,loginName);
        System.out.println(list);
        return list;
    }

}
