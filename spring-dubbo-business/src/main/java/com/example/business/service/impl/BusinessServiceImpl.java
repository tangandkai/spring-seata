package com.example.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.api.SeataAccountService;
import com.example.api.SeataOrderService;
import com.example.business.service.BusinessService;
import com.example.constant.DubboConstant;
import com.example.vo.SeataOrderVO;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Package: com.example.business.service.impl
 * Date: 2024/5/29
 * Time: 18:41
 * Description:
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @DubboReference(group = DubboConstant.GROUP,version = DubboConstant.VERSION)
    private SeataOrderService seataOrderService;

    @DubboReference(group = DubboConstant.GROUP,version = DubboConstant.VERSION)
    private SeataAccountService seataAccountService;

    @GlobalTransactional(name = "spring-dubbo-tx1",timeoutMills = 3000,rollbackFor = Exception.class)
    @Override
    public void doBusiness(String userId, String code, int count, long money) {
        // 上下文传递
        // 向提供方传递参数
        RpcContext.getClientAttachment().setAttachment("client","this is BusinessServiceImpl doBusiness");
        boolean b = seataAccountService.debit(userId, money);
        log.info("seataAccountService.debit result :[{}]",b);
        log.info("business start..........");
        SeataOrderVO seataOrderVO = seataOrderService.create(userId, code, count);
        log.info("seataOrderService.create :[{}]", JSON.toJSONString(seataOrderVO));
        // 获取提供方的上下文参数
        Map<String, Object> objectAttachments = RpcContext.getServerContext().getObjectAttachments();
        log.info("contextService return :[{}]",JSON.toJSONString(objectAttachments));
    }
}
