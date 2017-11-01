package com.lanou.account.mapper;

import com.lanou.account.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    void setState(Integer id);

    List<Account> findAllAccount();

    List<Account> like(@Param("idcard") String idcard,
                       @Param("realName") String realName,
                       @Param("loginName") String loginName);

    void setState1(Integer id);

    Account findIdCard(String idcard);
}