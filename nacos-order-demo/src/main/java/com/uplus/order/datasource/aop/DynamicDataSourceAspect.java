package com.uplus.order.datasource.aop;

import com.uplus.order.datasource.DynamicDataSourceContextHolder;
import com.uplus.order.datasource.ExchangeDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 切面实现动态数据源切换
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class DynamicDataSourceAspect {

    //调用的方法有该注解才会拦截
    /**
     * @within(xxx)"：如果某个类上标注了注解@A，那么该类中的所有方法就会被匹配为切点，并且该类的子类中没有重写的父类方法也会被匹配为切点
     * @target(xxx)"：如果某个类上标注了注解@A，那么该类中的所有方法就会被匹配为切点
     */
    @Pointcut("@annotation(com.uplus.order.datasource.ExchangeDataSource) || @within(com.uplus.order.datasource.ExchangeDataSource)")
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("开始切入切换数据源...");
        ExchangeDataSource exchangeDataSource = parseMethodWithExchangeDataSource(joinPoint);
        if (!Objects.isNull(exchangeDataSource)) {
            String merchantKey = exchangeDataSource.value();
            log.info("切换数据源成功merchantKey = " + merchantKey);
            DynamicDataSourceContextHolder.setDataSourceKey(merchantKey);
        }
        try {
            return joinPoint.proceed();
        } finally {
            log.info("切入数据源之后...");
            DynamicDataSourceContextHolder.removeDataSourceKey();
        }
    }

    private ExchangeDataSource parseMethodWithExchangeDataSource(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 如果类上有切换数据源的注解
        ExchangeDataSource annotation = null;
        if (targetClass.isAnnotationPresent(ExchangeDataSource.class)) {
             annotation = targetClass.getAnnotation(ExchangeDataSource.class);
        }
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(ExchangeDataSource.class)) {
            annotation=  method.getAnnotation(ExchangeDataSource.class);
        }
        return annotation;
    }
}

