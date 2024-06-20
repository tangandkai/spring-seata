package com.example.account.service;

import com.alibaba.fastjson.JSON;
import com.example.account.dao.SeataAccountDao;
import com.example.account.entity.SeataAccount;
import com.example.api.SeataAccountService;
import com.example.constant.DubboConstant;
import com.example.vo.SeataAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Package: com.example.account.service
 * Date: 2024/5/29
 * Time: 15:32
 * Description:
 */
@Slf4j
@Service
@DubboService(group = DubboConstant.GROUP,version = DubboConstant.VERSION)
public class SeataAccountServiceImpl implements SeataAccountService {

    private SeataAccountDao seataAccountDao;

    public SeataAccountServiceImpl(SeataAccountDao seataAccountDao){
        this.seataAccountDao = seataAccountDao;
    }

    @Override
    public boolean debit(String userId, long money) {
        if (money<0){
            return false;
        }
        try {
            // 接收客户端消息
            Map<String, Object> objectAttachments = RpcContext.getServerAttachment().getObjectAttachments();
            log.info("contextService serverAttachments: [{}]",JSON.toJSONString(objectAttachments));
            SeataAccount seataAccount = seataAccountDao.findByUserId(userId);
            // 扣减金额大于账户余额，扣减失败
            long deductMoney ;
            if (null == seataAccount || (deductMoney = seataAccount.getMoney()-money)<0){
                return false;
            }
            // 开始扣减
            seataAccount.setMoney(deductMoney);
            log.info("开始扣减金额: [{}]", JSON.toJSONString(seataAccount));
            seataAccountDao.save(seataAccount);
            // 向客户端传递消息
            RpcContext.getServerContext().setAttachment("serverAccount","this is account service");
        }catch (Exception e){
            log.error("金额扣减异常: [{}]",e.getMessage(),e);
            throw e;
        }
        return true;
    }

    @Override
    public SeataAccountVO getUserAccount(String userId) {
        SeataAccount byUserId = seataAccountDao.findByUserId(userId);
        SeataAccountVO seataAccountVO = new SeataAccountVO();
        BeanUtils.copyProperties(byUserId,seataAccountVO);
        return seataAccountVO;
    }
}
