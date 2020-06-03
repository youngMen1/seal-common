package com.seal.test.exception.enums;

/**
 * 异常枚举返回获取异常信息
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:05
 **/
public interface IResponseEnum {
    /**
     * 获取错误编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getMessage();
}
