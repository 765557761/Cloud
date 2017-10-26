package com.lanou.account.service;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.exception.AddException;

import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {

    void add(Account account,String psw) throws AddException;

//    List<Account> findAllAccount();

    List<Account> findwithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<Account> getPageInfo(Integer pageSize);

    Account selectById(Integer id);

    void update(Account account);

    void delById(Integer id);

    void setState(Integer id,Account account);

    List<Account> like(String idcard, String realName,String loginName);

    Account findIdCard(String idcard);
}
