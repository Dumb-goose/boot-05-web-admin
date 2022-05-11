package com.atguigu.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
@Deprecated
//@Configuration
public class MyDataSource {
    //在容器中默认如果没有其他的数据源则 配置希卡利数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            //加入监控页功能才可以开启的SQL监控功能("stat") 开启SQL防火墙("wall")
            dataSource.setFilters("stat,wall");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }

    //配置druid的监控页功能
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean =
                new ServletRegistrationBean<>(statViewServlet,"/druid/*");
        //登录监控页的登录设置
        registrationBean.addInitParameter("loginUsername","蔡徐坤");
        registrationBean.addInitParameter("loginPassword","123456");
        return registrationBean;
    }

    //WebStatFilter用于采集web-jdbc关联控制的数据

    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> registrationBean =
                new FilterRegistrationBean<WebStatFilter>(webStatFilter);
        //监控每一个查询数据库的请求信息
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return registrationBean;
    }
}
