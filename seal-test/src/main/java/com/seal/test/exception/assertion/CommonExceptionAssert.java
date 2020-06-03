package com.seal.test.exception.assertion;

import com.seal.test.exception.base.BaseException;
import com.seal.test.exception.enums.IResponseEnum;

import java.text.MessageFormat;

/**
 * 断言抛出异常信息
 *
 * @author fengzhiqiang
 * @date-time 2020/6/3 16:09
 **/
public interface CommonExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BaseException newException(Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new BaseException(this, args, message);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String message = MessageFormat.format(this.getMessage(), args);
        return new BaseException(this, args, message, t);
    }
}
