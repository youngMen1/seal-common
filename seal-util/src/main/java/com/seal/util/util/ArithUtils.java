package com.seal.util.util;

import java.math.BigDecimal;


/**
 * 数学运算工具类
 */
public class ArithUtils {

    /**
     * 默认运算精度
     */
    private static final int DEF_SCALE = 13;

    /**
     * 加法
     *
     * @param b1
     * @param bn
     * @return: BigDecimal
     */
    public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }

        if (null != bn) {
            for (BigDecimal b : bn) {
                b1 = b1.add(null == b ? BigDecimal.ZERO : b);
            }
        }
        return b1.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param b1
     * @param bn
     * @return: BigDecimal
     */
    public static BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {
        return safeSubtract(false, b1, bn);
    }

    /**
     * 减法
     *
     * @param isZero
     * @param b1
     * @param bn
     * @return: BigDecimal
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
        return (isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : r) : r).setScale(DEF_SCALE,
                BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法(小数两位后截断)
     *
     * @param b1
     * @param b2
     * @return: BigDecimal
     */
    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {
        if (null == b1 || null == b2) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(DEF_SCALE,
                BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法(小数两位后截断)
     *
     * @param b1
     * @param b2
     * @return: BigDecimal
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2) {
        return safeDivide(b1, b2, BigDecimal.ZERO);
    }

    /**
     * 除法(小数两位后截断)
     *
     * @param b1
     * @param b2
     * @param defaultValue
     * @return: BigDecimal
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
        if (null == b1 || null == b2) {
            return defaultValue;
        }

        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), DEF_SCALE,
                    BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 正负数相互转换
     *
     * @param b
     * @return
     */
    public static BigDecimal toNegate(BigDecimal b) {
        return b == null ? BigDecimal.ZERO : b.negate();
    }

    public static boolean isZero(BigDecimal b) {
        return b == null || b.equals(BigDecimal.ZERO);
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(6500);
        BigDecimal b = new BigDecimal(31);
        System.out.println(ArithUtils.safeAdd(a, b));
        System.out.println(ArithUtils.safeSubtract(a, b));
        System.out.println(ArithUtils.safeMultiply(a, b));
        System.out.println(ArithUtils.safeDivide(a, b));
        System.out.println(ArithUtils.safeMultiply(b, ArithUtils.safeDivide(a, b))
                .setScale(2, BigDecimal.ROUND_HALF_UP));
        BigDecimal repairAchRatio = BigDecimal.ZERO;
        repairAchRatio = null;

        if (repairAchRatio.compareTo(BigDecimal.ZERO) < 0) {

        }
    }
}