package com.lanou.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.exception.AddException;
import com.lanou.account.exception.TheSamePswException;
import com.lanou.account.mapper.AccountMapper;
import com.lanou.account.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;
    //添加
    public void add(Account account, String psw) throws AddException {
        if (account.getLoginPasswd().equals(psw)){
            account.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
            mapper.insertSelective(account);
            return;
        }
        throw new TheSamePswException();
    }

    public Account selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
    //修改
    public void update(Account account) {
        if (account.getStatus().equals("删除")){
            return;
        }
        System.out.println(account);
        mapper.updateByPrimaryKeySelective(account);
    }
    //删除
    public void delById(Integer id) {
        Account account = mapper.selectByPrimaryKey(id);
        if (account.getStatus().equals("删除")){
            return;
        }
        account.setStatus("删除");
        account.setCloseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
        mapper.updateByPrimaryKeySelective(account);
    }
    //状态
    public void setState(Integer id,Account account) {
        if (account.getStatus().equals("删除")){
            return;
        }
        if (account.getStatus().equals("暂停")){
            account.setCreateDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            account.setStatus("开通");
            account.setPauseDate(null);
            mapper.updateByPrimaryKeySelective(account);
            return;
        }
        if (account.getStatus().equals("开通")){
            account.setPauseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));
            account.setStatus("暂停");
            mapper.updateByPrimaryKeySelective(account);
        }
    }

    public List<Account> like(String idcard, String realName,String loginName) {
        return mapper.like(idcard,realName,loginName);
    }

    public Account findIdCard(String idcard) {
        return mapper.findIdCard(idcard);
    }

    public List<Account> findwithPageInfo(Integer pageNo, Integer pageSize) {
        // 目标：获取PageInfo对象
        PageInfo<Account> pageInfo = queryStudentByPage(pageNo, pageSize);
        return pageInfo.getList();
//        System.out.println(list);
    }
    // 获取分页信息
    public PageInfo<Account> getPageInfo(Integer pageSize) {
        return queryStudentByPage(null, pageSize);
    }
    public PageInfo<Account> queryStudentByPage(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 6 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        // 获取全部学员
        List<Account> accountList = mapper.findAllAccount();
//        System.out.println("findAllAccount");
//        System.out.println(accountList);
        // 使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);
//        System.out.println(pageInfo);
        return pageInfo;
    }

}
