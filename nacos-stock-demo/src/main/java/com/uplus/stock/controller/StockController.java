package com.uplus.stock.controller;

import com.uplus.stock.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "库存控制层")
@RequiredArgsConstructor
@RequestMapping(value = "stock")
public class StockController {


    @ApiOperation(value = "减少库存")
    @GetMapping(value = "/reduct")
    public R<?> reduct(@RequestParam("id") Long id) {
        System.out.println(id+"的库存减少啦");
        return R.ok("减少库存成功");
    }

}
