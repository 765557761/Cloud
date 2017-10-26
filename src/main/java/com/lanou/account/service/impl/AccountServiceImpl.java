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
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;

    public void add(Account account, String psw) throws AddException {
        if (account.getLoginPasswd().equals(psw)){
            mapper.insertSelective(account);
            return;
        }
        throw new TheSamePswException();
    }

    public Account selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void update(Account account) {
        mapper.updateByPrimaryKeySelective(account);
    }

    public void delById(Integer id) {
        mapper.setState1(id);
    }

    public void setState(Integer id,String status) {
        if (status.equals("0")){
            mapper.setState(id);
        }
        if (status.equals("1")){
            mapper.setState1(id);
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
