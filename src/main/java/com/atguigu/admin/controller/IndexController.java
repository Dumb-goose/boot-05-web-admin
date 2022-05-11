package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.mapper.AccountMapper;
import com.atguigu.service.AccountService;
import com.atguigu.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountService accountService;

    @Autowired
    CityService cityService;

    @PostMapping("/city")
    public City saveCity(City city){
        City saveCity = cityService.saveCity(city);
        return saveCity;
    }

    @GetMapping("/city/{id}")
    @ResponseBody
    public City getCityById(@PathVariable("id") Long id) {
        City cityById = cityService.getCityById(id);
        return cityById;
    }

    @GetMapping("/acct/{id}")
    @ResponseBody
    public Account getById(@PathVariable("id") Long id) {
        Account acct = accountService.getAcctById(id);
        return acct;
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb() {
        String sql = "select count(*) from account_tbl ";
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        return result.toString();
    }

    @RequestMapping({"/", "/login"})
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session) {
        if (!(StringUtils.isEmpty(user.getUserName())) && !(StringUtils.isEmpty(user.getPassword()))) {
            session.setAttribute("loginUser", user);
            return "redirect:/main.html";
        }
        //重新登录
        log.info("登录信息错误,请重新登录!");
        session.setAttribute("msg", "账号或者密码错误!");
        return "login";


    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session) {

        log.info("当前方式是:{}", "mainPage");
        return "main";

    }
}
