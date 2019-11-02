package com.seal.util.httpresultutil;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/2 09:31
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult {

    private String status;

    private String message;

    private Object data;

}
