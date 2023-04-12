package com.uplus.order.datasource.config;


import com.uplus.order.datasource.DataSourceConstant;
import com.uplus.order.datasource.DataSourceProperties;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源属性配置
 *
 */
@RefreshScope
@ConfigurationProperties(prefix = DataSourcePropertiesConfig.PREFIX)
public class DataSourcePropertiesConfig {
    /**
     * 前缀
     */
    public static final String PREFIX = "spring.datasource.dynamic.datasource";


    @Setter
    private DataSourceProperties master;

    @Setter
    private DataSourceProperties slave;

    public Map<String, DataSourceProperties> getDataSourceConfig() {
        Map<String, DataSourceProperties> resultMap = new HashMap<>(16);
        resultMap.put(DataSourceConstant.MASTER, master);
        resultMap.put(DataSourceConstant.SLAVE, slave);
        return resultMap;
    }
}

