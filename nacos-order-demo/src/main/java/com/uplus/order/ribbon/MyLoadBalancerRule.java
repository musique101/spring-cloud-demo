package com.uplus.order.ribbon;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


@Slf4j
public class MyLoadBalancerRule extends AbstractLoadBalancerRule {

    @Resource
    private NacosServiceManager nacosServiceManager;

    // 实现自定义的负载均衡算法
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            log.info("key:{}", o);
            BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            log.info("baseLoadBalancer--->:{}", baseLoadBalancer);
            //获取微服务的名称
            String serviceName = baseLoadBalancer.getName();
            //获取Nacos服务发现的相关组件API
            NamingService namingService = nacosServiceManager.getNamingService();
            //获取 一个基于nacos client 实现权重的负载均衡算法
            Instance instance = namingService.selectOneHealthyInstance(serviceName);
            //返回一个server
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("自定义负载均衡算法错误");
        }
        return null;
    }
}
