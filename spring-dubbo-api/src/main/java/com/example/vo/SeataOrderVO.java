package com.example.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.example.vo
 * Date: 2024/5/29
 * Time: 18:30
 * Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeataOrderVO implements Serializable {

    private Long id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Long money;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
