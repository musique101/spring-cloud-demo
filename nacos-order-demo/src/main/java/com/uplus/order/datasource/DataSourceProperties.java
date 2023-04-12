package com.uplus.order.datasource;


import lombok.Data;

/**
 * 数据库连接属性
 */
@Data
public class DataSourceProperties {

    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}

