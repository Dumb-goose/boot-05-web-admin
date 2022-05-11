package com.atguigu.admin.servlet;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;

@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {
    @Bean
    public ServletRegistrationBean myServlet() {
        MyServlet myServlet = new MyServlet();
        ServletRegistrationBean<Servlet> registrationBean =
                new ServletRegistrationBean<>(myServlet, "/my", "/my02");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(myFilter);
//        return new FilterRegistrationBean(myFilter,myServlet());
        registrationBean.setUrlPatterns(Arrays.asList("/my", "/css/*"));
        return registrationBean;

    }

    @Bean
    public ServletListenerRegistrationBean registrationBean() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        MyServletContextListener listener = new MyServletContextListener();
        registrationBean.setListener(listener);
        return registrationBean;
    }
}
