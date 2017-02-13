package com.xd.config.db;

/**
 * @author xhc
 * @version V
 * @Package com.xd.config
 * @Description:数据库配置文件
 * @date 2016-02-29 10:59
 */
public class DbProp {
    private String user;
    private String passwd;
    private String url;
    private String driverClassName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
