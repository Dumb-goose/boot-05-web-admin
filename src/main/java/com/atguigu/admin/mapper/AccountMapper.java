package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.Account;
import org.apache.ibatis.annotations.Param;
public interface AccountMapper {


    public Account getAcct(@Param("id") Long id);
}
