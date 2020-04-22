package com.seal.util.dateutil;


import com.seal.util.constant.TimeConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description 日期格式化辅助类(线程安全)
 *
 * SimpleDateFormat不是线程安全所以每次都去new
 *
 * 说明：日期格式中的这两对字母表意如下：
 *  1、表示月份是大写的 M；
 *  2、表示分钟则是小写的 m；
 *  3、24 小时制的是大写的 H；
 *  4、12 小时制的则是小写的 h。
 **/
public final class DateTimeUtils {


    private static final String DATE_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN_SLASH = "yyyy/MM/dd";
    private static final String DATE_PATTERN_SLASH_DMY = "dd/MM/yyyy";
    private static final String DATE_PATTERN_LINE_YMD = "yyyy-MM-dd";
    private static final String DATE_PATTERN_NONE = "yyyyMMddHHmmssSSS";
    private static final String DATE_PATTERN_NONE_YM = "yyyyMM";


    /**
     * 线程安全的日期格式对象 yyyy-MM-dd HH:mm:ss
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_LINE = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {

            return new SimpleDateFormat(DATE_PATTERN_LINE);
        }

    };

    /**
     * 线程安全的日期格式对象 yyyy/MM/dd
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_SLASH = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN_SLASH);
            simpleDateFormat.setLenient(false);
            return simpleDateFormat;
        }

    };

    /**
     * 线程安全的日期格式对象 yyyyMMddHHmmssSSS
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_NONE = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {

            return new SimpleDateFormat(DATE_PATTERN_NONE);
        }

    };

    /**
     * 线程安全的日期格式对象yyyy-MM-dd
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_LINE_YMD = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {

            return new SimpleDateFormat(DATE_PATTERN_LINE_YMD);
        }

    };

    /**
     * 线程安全的日期格式对象dd/MM/yyyy
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_SLASH_DMY = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {

            return new SimpleDateFormat(DATE_PATTERN_SLASH_DMY);
        }

    };

    /**
     * 线程安全的日期格式对象yyMM
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_NONE_YM = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {

            return new SimpleDateFormat(DATE_PATTERN_NONE_YM);
        }

    };

    /**
     * 格式化完整日期
     *
     * @param date
     * @return yyyy/MM/dd格式的字符串
     */
    public static String formatDateSlash(Date date) {
        return DATE_FORMAT_SLASH.get().format(date);
    }

    /**
     * 格式化完整日期
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */

    public static String formatDateLine(Date date) {
        return DATE_FORMAT_LINE.get().format(date);
    }

    /**
     * 格式化完整日期
     *
     * @param date
     * @return yyyyMMddHHmmssSSS格式的字符串
     */
    public static String formatDateNone(Date date) {
        return DATE_FORMAT_NONE.get().format(date);
    }

    /**
     * 格式化年月日
     *
     * @param date
     * @return yyyy-MM-dd格式的字符串
     */
    public static String formatDateLineYMD(Date date) {
        return DATE_FORMAT_LINE_YMD.get().format(date);
    }

    /**
     * 格式化年月
     *
     * @param date
     * @return yyyyMM格式的字符串
     */
    public static String formatDateLineYM(Date date) {
        return DATE_FORMAT_NONE_YM.get().format(date);
    }

    /**
     * yyyy/MM/dd格式的字符串 转date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseFormatSlash(String strDate) {
        try {
            return DATE_FORMAT_SLASH.get().parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * dd/MM/yyyy格式的字符串 转date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseFormatSlashDMY(String strDate) {
        try {
            return DATE_FORMAT_SLASH_DMY.get().parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd格式的字符串 转date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseFormatLineYMD(String strDate) {
        try {
            return DATE_FORMAT_LINE_YMD.get().parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式的字符串 转date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseFormatLine(String strDate) {
        try {
            return DATE_FORMAT_LINE.get().parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * yyyyMM格式的字符串 转date
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parseFormatYM(String strDate) {
        try {
            return DATE_FORMAT_NONE_YM.get().parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 指定日期格式为四位年两位月份，注意yyyyMM区分大小写；
     */
    public static boolean isValidMonthDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 指定日期格式为四位年两位季度，注意yyyyMM区分大小写；
     */

    public static boolean isValidSeasonsDate(String str) {
        boolean convertSuccess = true;

        if (str.length() != 6) {
            return false;
        }
        String year = str.substring(0, 4);
        String seasons = str.substring(4);
        if (!TimeConstant.FRIST_SEASON.equals(seasons)
                && !TimeConstant.SECOND_SEASON.equals(seasons)
                && !TimeConstant.THIRD_SEASON.equals(seasons)
                && !TimeConstant.FOURTH_SEASON.equals(seasons)) {
            return false;
        }
        convertSuccess = isValidYearDate(year);
        return convertSuccess;
    }


    /**
     * 指定日期格式为四位年，注意yyyyMM区分大小写；
     */

    public static boolean isValidYearDate(String str) {
        boolean convertSuccess = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
