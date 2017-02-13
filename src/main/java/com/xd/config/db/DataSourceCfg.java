package com.xd.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author xhc
 * @version V
 * @Package com.xd.config
 * @Description: 数据库数据源及连接池配置
 * @date 2016-02-29 10:10
 */
@Service
public class DataSourceCfg {
    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid*//*");
    }

    @Autowired
    DbProp dbProp;

    @Bean(name = "dataSource")
    public DruidDataSource dataSource() {
        System.out.println();
        System.out.println(dbProp.getUser());
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(dbProp.getDriverClassName());
        ds.setUrl(dbProp.getUrl());
        ds.setUsername(dbProp.getUser());
        ds.setPassword(dbProp.getPasswd());
        ds.setDefaultAutoCommit(false);
        try {
            ds.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
