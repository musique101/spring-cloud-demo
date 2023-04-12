package com.uplus.order.controller;

import com.uplus.order.common.R;
import com.uplus.order.feign.StockFeign;
import com.uplus.order.service.OrderServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "订单控制层")
@RequiredArgsConstructor
@RequestMapping(value = "order")
@RefreshScope
public class OrderController {

    @Value("${user}")
    private String user;

    @Autowired
    private StockFeign stockFeign;

    @Autowired
    private OrderServer orderServer;

    @ApiOperation(value = "下单")
    @GetMapping(value = "/booking")
    public R<?> booking(@RequestParam("id") Long id) {
        stockFeign.reduct(id);
        return R.ok(user + "下单成功");
    }


    @ApiOperation(value = "测试配置中心jpa")
    @GetMapping(value = "/get")
    public R<?> getJpa(@RequestParam("id") Long id) {
        return orderServer.getOne(id);
    }

    @ApiOperation(value = "测试配置中心mybatis")
    @GetMapping(value = "/getM")
    public R<?> getMybatis(@RequestParam("id") Long id) {
        return orderServer.getOneBatis(id);
    }

    @ApiOperation(value = "测试元数据")
    @GetMapping(value = "/getMeta")
    public R<?> getMeta(@RequestParam("key") String key) {
        return orderServer.getMeta(key);
    }
}
