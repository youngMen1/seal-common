package com.seal.test.exception.enums;

import com.seal.test.exception.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:09
 **/
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements CommonExceptionAssert {
    /**
     * 加载失败
     */
    ERROR("9999", "加载失败"),

    /**
     * 服务器繁忙
     */
    SERVER_ERROR("9998", "服务器繁忙"),

    /**
     * 数据错误
     */
    DATA_ERROR("9996", "数据错误"),
    ;

    /**
     * 返回编码
     */
    String code;

    /**
     * 返回信息
     */
    String message;

}
