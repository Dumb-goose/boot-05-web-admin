package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import com.atguigu.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class TableController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("basic_table")
    public String basic_table() {
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {

//        List<User> userList = Arrays.asList(new User("蔡徐坤", "123456")
//                , new User("马保国", "123456"), new User("鸡哥", "123456"), new User("特工穿山甲", "123456"));
//        model.addAttribute("userList", userList);
        //查出数据库中user表的用户进行信息的展示
        List<User> list = userService.list();
        Page<User> userPage = new Page<User>(pn, 2);
        Page<User> page = userService.page(userPage, null);
        model.addAttribute("page", page);

        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                             RedirectAttributes ra
    ) {
        ra.addAttribute("pn", pn);
        userService.removeById(id);
        return "redirect:/dynamic_table?";

    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/pricing_table")
    public String pricing_table() {
        return "table/pricing_table";
    }
}
