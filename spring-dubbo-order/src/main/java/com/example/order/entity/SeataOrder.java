package com.example.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.example.order.entity
 * Date: 2024/5/29
 * Time: 17:20
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_tbl")
public class SeataOrder implements Serializable {

    /** 自增逐渐id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    /** 用户id*/
    @Column(name = "user_id",nullable = false)
    private String userId;

    /** 商品编号*/
    @Column(name = "commodity_code",nullable = false)
    private String commodityCode;

    /** 商品数量*/
    @Column(name = "count",nullable = false)
    private Integer count;

    /** 用户金额*/
    @Column(name = "money")
    private Long money;

    /** 创建时间*/
    @Column(name = "create_time")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /** 更新时间*/
    @Column(name = "update_time")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public SeataOrder(String userId, String commodityCode, int orderCount){
        this.userId = userId;
        this.commodityCode = commodityCode;
        this.count = orderCount;
    }
}
