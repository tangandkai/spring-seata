package com.example.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Package: com.example.account.vo
 * Date: 2024/5/29
 * Time: 16:19
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {

    private String code;

    private String desc;

    private T data;

}
