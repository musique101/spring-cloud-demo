package com.uplus.order.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFeignConfig {

    @Bean
    public IRule myRule(){
        return new MyLoadBalancerRule();
    }
}
