package com.example.order.dao;

import com.example.order.entity.SeataOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.example.order.dao
 * Date: 2024/5/29
 * Time: 17:21
 * Description:
 */
@Repository
public interface SeataOrderDao extends PagingAndSortingRepository<SeataOrder,Long> {

    List<SeataOrder> findByUserId(String userId);
}
