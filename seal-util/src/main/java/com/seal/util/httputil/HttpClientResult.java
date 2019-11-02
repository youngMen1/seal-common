package com.seal.util.httputil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/2 13:54
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;


}
