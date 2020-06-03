package com.seal.test.exception;

import com.seal.test.exception.base.BaseException;
import com.seal.test.exception.enums.IResponseEnum;

/**
 * 业务异常
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:09
 **/
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param responseEnum
     * @param args
     * @param message
     */
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    /**
     *
     * @param responseEnum
     * @param args
     * @param message
     * @param cause
     */
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }

}
