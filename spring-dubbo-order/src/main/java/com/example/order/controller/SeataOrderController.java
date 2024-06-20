package com.example.order.controller;

import com.example.api.SeataOrderService;
import com.example.mvc.annotation.IgnoreResponseAdvice;
import com.example.vo.SeataOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Package: com.example.order.controller
 * Date: 2024/5/29
 * Time: 17:56
 * Description:
 */
@RestController
public class SeataOrderController {

    @Autowired
    private SeataOrderService seataOrderService;

    @IgnoreResponseAdvice
    @RequestMapping("/create")
    public SeataOrderVO create(@RequestParam(value = "userId") String userId,
                               @RequestParam(value = "commodityCode") String commodityCode,
                               @RequestParam(value = "orderCount") int orderCount){
        return seataOrderService.create(userId, commodityCode, orderCount);
    }

    @RequestMapping("/queryOrders")
    public List<SeataOrderVO> queryOrders(@RequestParam(value = "userId") String userId){
        return seataOrderService.queryOrders(userId);
    }
}
