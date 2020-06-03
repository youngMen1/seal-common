package com.seal.test.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:38
 **/
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
