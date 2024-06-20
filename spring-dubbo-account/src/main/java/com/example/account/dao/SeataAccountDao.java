package com.example.account.dao;

import com.example.account.entity.SeataAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Package: com.example.account.dao
 * Date: 2024/5/29
 * Time: 15:21
 * Description:
 */
@Repository
public interface SeataAccountDao extends PagingAndSortingRepository<SeataAccount,Long> {

    @Query(value = "select * from account_tbl where user_id= :userId" ,nativeQuery = true)
    SeataAccount findByUserId(@Param("userId") String userId);
}
