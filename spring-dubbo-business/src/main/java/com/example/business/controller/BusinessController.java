package com.example.business.controller;

import com.example.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package: com.example.business.controller
 * Date: 2024/5/29
 * Time: 18:23
 * Description:
 */
@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/exec")
    public void exec(@RequestParam(value = "userId") String userId,
                     @RequestParam("code") String code,
                     @RequestParam("count") int count,
                     @RequestParam("money") long money){
        businessService.doBusiness(userId, code, count, money);
    }
}
