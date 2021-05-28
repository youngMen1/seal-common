package com.seal.util.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

/**
 * @author fengzhiqiang
 * @date 2021/5/27 10:25
 **/
public class BigDecimalUtil {

    /**
     * 将null转换为0
     */
    public static BigDecimal bigDecimalNullToZero(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        } else {
            return bigDecimal;
        }
    }

    /**
     * 加法
     */
    public static BigDecimal bigDecimalAddList(List<BigDecimal> decimalList) {
        return decimalList
                .stream()
                .map(BigDecimalUtil::bigDecimalNullToZero)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, HALF_UP);
    }



    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * 默认返回小数位后2位，用于金额计算
     * @author : shijing
     * 2017年3月23日下午4:59:29
     * @param b1
     * @param b2
     * @param defaultValue
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
        if (null == b1 || null == b2) {
            return defaultValue;
        }
        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2,
                    BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * BigDecimal的安全减法运算
     * @param isZero 减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果
     * @param b1 被减数
     * @param bn 需要减的减数数组
     * @return
     */
    public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        BigDecimal r = b1;
        if (null != bn) {
            for (BigDecimal b : bn) {
                r = r.subtract((null == b ? BigDecimal.ZERO : b));
            }
        }
        return isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : r) : r;
    }

    public static void main(String[] args) {
        ArrayList<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("10"));





        System.out.println(bigDecimalAddList(list));

        System.out.println(safeDivide(new BigDecimal(30),new BigDecimal(100),new BigDecimal(0)));
    }
}
