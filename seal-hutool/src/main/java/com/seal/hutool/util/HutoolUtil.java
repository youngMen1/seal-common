package com.seal.hutool.util;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 17:22
 * @description Hutool工具类
 **/
@Slf4j
public class HutoolUtil {

    public static void main(String[] args) {
        dateUtil();
        dateUtilTest();
    }

    /**
     * DateUtil.parse方法会自动识别一些常用格式，包括：
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd
     * HH:mm:ss
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd HH:mm:ss.SSS
     */

    private static void dateUtil() {

        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        System.out.println("date:{}" + date);

        /**
         * 也可以使用自定义日期格式转化：
         */
        String dateStr2 = "2017-03-01";
        Date date2 = DateUtil.parse(dateStr2, "yyyy-MM-dd");
        System.out.println("date2:{}" + date2);

        /**
         * 格式化日期输出
         */
        String dateStr3 = "2018-05-01";
        Date date3 = DateUtil.parse(dateStr3);
        System.out.println("date3:{}" + date3);


        // 结果 2018/05/01
        String format = DateUtil.format(date3, "yyyy/MM/dd");
        System.out.println("format:{}" + format);

        // 常用格式的格式化，结果：2018-05-01
        String formatDate = DateUtil.formatDate(date3);
        System.out.println("formatDate:{}" + formatDate);

        // 结果：2017-05-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date3);
        System.out.println("formatDateTime:{}" + formatDateTime);

        // 昨天
        System.out.println("昨天:{}" + DateUtil.yesterday());
        // 明天
        System.out.println("明天:{}" + DateUtil.tomorrow());
        // 上周
        System.out.println("上周:{}" + DateUtil.lastWeek());
        // 下周
        System.out.println("下周:{}" + DateUtil.nextWeek());
        // 上个月
        System.out.println("上个月:{}" + DateUtil.lastMonth());
        // 下个月
        System.out.println("下个月:{}" + DateUtil.nextMonth());
    }

    /**
     * 日期时间差
     * 有时候我们需要计算两个日期之间的时间差（相差天数、相差小时数等等），Hutool将此类方法封装为between方法：
     */
    private static void dateUtilTest() {
        System.out.println("------------------------------------");
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        // 相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(betweenDay);
        // 有时候我们希望看到易读的时间差，比如XX天XX小时XX分XX秒，此时使用DateUtil.formatBetween方法：
        // Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(betweenDay, BetweenFormater.Level.MINUTE);
        // 输出：31天1小时
        System.out.println(formatBetween);
       // Console.log(formatBetween);


        /**
         * 其他时间
         */
        // 年龄
        System.out.println("年龄:{}"+DateUtil.ageOfNow("1990-01-30"));

         // 是否闰年
        System.out.println("是否闰年:{}"+DateUtil.isLeapYear(2017));
    }

}
