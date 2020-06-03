package com.seal.test.exception.base;

import com.seal.test.exception.enums.IResponseEnum;
import lombok.Getter;

/**
 * 异常类负责收集异常信息
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:07
 **/
public class BaseException extends RuntimeException {
    /**
     * 异常信息
     */
    @Getter
    protected IResponseEnum iResponseEnum;

    /**
     * 参数
     */
    @Getter
    protected Object[] args;


    public BaseException(IResponseEnum iResponseEnum) {
        this.iResponseEnum = iResponseEnum;
    }


    public BaseException(IResponseEnum iResponseEnum, Object[] args, String message) {
        super(message);
        this.iResponseEnum = iResponseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum iResponseEnum, Object[] args, String message, Throwable e) {
        super(message, e);
        this.iResponseEnum = iResponseEnum;
        this.args = args;
    }


}
