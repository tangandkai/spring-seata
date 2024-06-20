package com.example.api;

import com.example.vo.SeataOrderVO;

import java.util.List;

/**
 * Package: com.example.order.service
 * Date: 2024/5/29
 * Time: 17:22
 * Description:
 */
public interface SeataOrderService {

    /**
     * 创建订单
     * @param userId            用户id
     * @param commodityCode     商品编号
     * @param orderCount        订购数量
     */
    SeataOrderVO create(String userId, String commodityCode, int orderCount);


    /**
     * 查询订单信息
     * @param userId 用户编号
     * @return
     */
    List<SeataOrderVO> queryOrders(String userId);
}
