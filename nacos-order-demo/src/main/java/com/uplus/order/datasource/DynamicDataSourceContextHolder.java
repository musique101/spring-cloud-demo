package com.uplus.order.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 动态数据源上下文保持类
 *
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    /**
     * 动态数据源的上下文
     */
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_MERCHANT_HOLDER = new InheritableThreadLocal<>();

    /**
     * 切换数据源
     *
     * @param merchant 租户Key
     */
    public static void setDataSourceKey(String merchant) {
        log.info("切换数据源：" + merchant);
        DATASOURCE_CONTEXT_MERCHANT_HOLDER.set(merchant);
    }

    /**
     * 获取当前数据源名称
     *
     * @return 当前数据源名称
     */
    public static String getDataSourceKey() {
        return Optional.ofNullable(DATASOURCE_CONTEXT_MERCHANT_HOLDER.get())
                .orElse(DataSourceConstant.MASTER);
    }

    /**
     * 删除当前数据源名称
     */
    public static void removeDataSourceKey() {
        DATASOURCE_CONTEXT_MERCHANT_HOLDER.remove();
    }
}
