package com.uplus.order.feign;

import com.uplus.order.ribbon.MyFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "stock-service", configuration = MyFeignConfig.class)
public interface StockFeign {

    @GetMapping("/stock/reduct")
    String reduct(@RequestParam("id") Long id);
}

