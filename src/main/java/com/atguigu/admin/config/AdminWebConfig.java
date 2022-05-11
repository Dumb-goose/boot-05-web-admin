package com.atguigu.admin.config;

import com.atguigu.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
   1.编写一个拦截器实现HandlerInterceptor接口
   2.拦截器注册到容器中(实现WebMvcConfigurer的addInterceptors)
   3.指定拦截规则(如果拦截所有，静态资源也会拦截)

 */
@Configuration(proxyBeanMethods = true)
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/city/*","/acct/**","/sql","/","/css/**", "/fonts/**", "/images/**", "/login","/js/**");
    }


}
