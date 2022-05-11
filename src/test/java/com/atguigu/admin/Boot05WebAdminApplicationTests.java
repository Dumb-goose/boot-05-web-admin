package com.atguigu.admin;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        String sql = "select count(*) from account_tbl ";
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        log.info("本次查询结果一共有{}条",result);
        log.info("数据源类型为{}",dataSource.getClass());

    }

    @Test
    void testUserMapper(){
        User user = userMapper.selectById(1L);

        log.info("用户信息:{}",user);
    }

}
