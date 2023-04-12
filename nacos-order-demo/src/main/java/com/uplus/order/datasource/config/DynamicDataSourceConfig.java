package com.uplus.order.datasource.config;

import com.uplus.order.datasource.DataSourceConstant;
import com.uplus.order.datasource.DataSourceProperties;
import com.uplus.order.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Configuration
public class DynamicDataSourceConfig {
    @Resource
    private DataSourcePropertiesConfig dataSourcePropertiesConfig;

    private DataSource dataSource(DataSourceProperties dataSourceProperties){
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }

    @Primary
    @Bean
    @RefreshScope
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(16);
        Map<String, DataSourceProperties> dataSourcePropertiesMap = dataSourcePropertiesConfig.getDataSourceConfig();
        dataSourcePropertiesMap.forEach((merchant, properties) -> dataSourceMap.put(merchant, dataSource(properties)));
        // 设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMap.get(DataSourceConstant.MASTER));
        return dynamicDataSource;
    }


}

