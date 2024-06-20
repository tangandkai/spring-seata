package com.example.order.service;

import com.alibaba.fastjson.JSON;
import com.example.api.SeataOrderService;
import com.example.constant.DubboConstant;
import com.example.order.dao.SeataOrderDao;
import com.example.order.entity.SeataOrder;
import com.example.vo.SeataOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package: com.example.order.service.impl
 * Date: 2024/5/29
 * Time: 17:50
 * Description:
 */
@Slf4j
@Service
@DubboService(group = DubboConstant.GROUP,version = DubboConstant.VERSION)
public class SeataOrderServiceImpl implements SeataOrderService {

    private SeataOrderDao seataOrderDao;
    public SeataOrderServiceImpl(SeataOrderDao seataOrderDao){
        this.seataOrderDao = seataOrderDao;
    }

    @Override
    public SeataOrderVO create(String userId, String commodityCode, int orderCount) {
        // 接收客户端消息
        Map<String, Object> objectAttachments = RpcContext.getServerAttachment().getObjectAttachments();
        log.info("contextService serverAttachments: [{}]",JSON.toJSONString(objectAttachments));
        SeataOrder seataOrder = new SeataOrder(userId,commodityCode,orderCount);
        SeataOrder save = seataOrderDao.save(seataOrder);
        SeataOrderVO seataOrderVO = new SeataOrderVO();
        BeanUtils.copyProperties(save,seataOrderVO);
        // 向客户端传递消息
        RpcContext.getServerContext().setAttachment("serverOrder","this is order service");
        return seataOrderVO;
    }

    @Override
    public List<SeataOrderVO> queryOrders(String userId) {
        List<SeataOrder> byUserId = seataOrderDao.findByUserId(userId);
        log.info("orders by userId :[{}],[{}]",userId, JSON.toJSONString(byUserId));
        List<SeataOrderVO> res = new ArrayList<>(byUserId.size());
        byUserId.forEach(s->{
            SeataOrderVO seataOrderVO = new SeataOrderVO();
            BeanUtils.copyProperties(s,seataOrderVO);
            res.add(seataOrderVO);
        });
        log.info("orders by userId :[{}],[{}]",userId, JSON.toJSONString(res));
        return res;
    }
}
