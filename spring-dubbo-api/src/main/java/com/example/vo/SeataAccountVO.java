package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.example.api.vo
 * Date: 2024/5/29
 * Time: 18:28
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeataAccountVO implements Serializable {

    private Long id;

    private String userId;

    private Long money;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
