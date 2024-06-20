package com.example.api;

import com.example.vo.SeataAccountVO;

/**
 * Package: com.example.account.service
 * Date: 2024/5/29
 * Time: 15:30
 * Description:
 */
public interface SeataAccountService {

    /**
     * 余额扣减
     * @param userId   用户id
     * @param money    扣减金额
     * @return
     */
    boolean debit(String userId,long money);

    /**
     * 用户账户查询
     * @param userId
     * @return
     */
    SeataAccountVO getUserAccount(String userId);
}
