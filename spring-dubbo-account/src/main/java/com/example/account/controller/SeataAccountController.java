package com.example.account.controller;

import com.example.api.SeataAccountService;
import com.example.vo.SeataAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package: com.example.account.controller
 * Date: 2024/5/29
 * Time: 16:15
 * Description:
 */
@RestController
public class SeataAccountController {

    @Autowired
    private SeataAccountService seataAccountService;

    @RequestMapping("/getUserAccount")
    public SeataAccountVO getUserAccount(@RequestParam("userId") String userId){
        return seataAccountService.getUserAccount(userId);
    }

    @RequestMapping("/debit")
    public boolean debit(@RequestParam(value = "userId") String userId, @RequestParam(value = "money") long money){
        return seataAccountService.debit(userId,money);
    }
}
