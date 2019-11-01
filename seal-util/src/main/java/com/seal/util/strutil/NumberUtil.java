package com.seal.util.strutil;


import java.text.*;
import java.util.Calendar;

import static java.lang.Math.random;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/10/21 14:32
 * @description
 **/
public class NumberUtil {


    /**
     * This Format for format the data to special format.
     */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");

    /**
     * This Format for format the number to special format.
     */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");

    /**
     * This int is the sequence number ,the default value is 0.
     */
    private static int seq = 0;
    private static int i = 0;
    private static final int MAX = 9999;
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    public static synchronized String generateSequenceNo() {
        Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        return sb.toString();
    }

    public static String getNumber() {
        // 生成的6位数随机数
        return "SZCW" + (int) ((random() * 9 + 1) * 100000) + "";
    }

    /**
     * 生成的6位数随机数
     *
     * @return
     */
    public static String createRandom() {
        return (int) ((random() * 9 + 1) * 100000) + "";
    }

    /**
     * 生成劵码编号
     *
     * @return
     */
    public static String getCouponCode() {
        // 生成的6位数随机数
        return "CP" + (int) ((random() * 9 + 1) * 100000) + "";
    }

    /**
     * 生成16位卡号
     * 其中：
     * 6：类型1，
     * 8：类型2，
     * 9：类型3 【根据自己的业务定义】
     * 其他则会返回异常
     *
     * @return
     */
    public synchronized static String getCardNumber() {
        String st = "688" + "6" + getUnixTime();
        return st + getBankCardCheckCode(st);
    }

    /***
     * 获取当前系统时间戳 并截取
     * @return
     */
    private synchronized static String getUnixTime() {
        try {
            // 线程同步执行，休眠10毫秒 防止卡号重复
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        i = i > 100 ? i % 10 : i;
        return ((System.currentTimeMillis() / 100) + "").substring(1) + (i % 10);
    }

    /**
     * 从不含校验位的卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static void main(String[] args) {
        System.out.println(getCardNumber());
    }

}
