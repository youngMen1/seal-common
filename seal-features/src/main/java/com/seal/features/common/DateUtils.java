package com.seal.features.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author fengzhiqiang
 * @date 2018-04-21
 * @since 1.0
 * <p>
 * DateTimeFormatter我们更多的是直接使用pattern来做转换，
 * 其实这个类本身已经提供了一些预定义好的实例供我们使用。
 * 下面把两者的具体释义和示例都贴出来供大家参考。
 * <p>
 * 预定义
 * Predefined Formatters                       Formatter Description                                               Example
 * ----------------------                      ----------------------                                              ------------
 * ofLocalizedDate(dateStyle)                  Formatter with date style from the locale                           '2011-12-03'
 * ofLocalizedTime(timeStyle)                  Formatter with time style from the locale                           '10:15:30'
 * ofLocalizedDateTime(dateTimeStyle)          Formatter with a style for date and time from the locale            '3 Jun 2008 11:05:30'
 * ofLocalizedDateTime(dateStyle,timeStyle)    Formatter with date and time styles from the locale                 '3 Jun 2008 11:05'
 * BASIC_ISO_DATE                              Basic ISO date                                                      '20111203'
 * ISO_LOCAL_DATE                              ISO Local Date                                                      '2011-12-03'
 * ISO_OFFSET_DATE                             ISO Date with offset                                                '2011-12-03+01:00'
 * ISO_DATE                                    ISO Date with or without offset                                     '2011-12-03+01:00'; '2011-12-03'
 * ISO_LOCAL_TIME                              Time without offset                                                 '10:15:30'
 * ISO_OFFSET_TIME                             Time with offset                                                    '10:15:30+01:00'
 * ISO_TIME                                    Time with or without offset                                         '10:15:30+01:00'; '10:15:30'
 * ISO_LOCAL_DATE_TIME                         ISO Local Date and Time                                             '2011-12-03T10:15:30'
 * ISO_OFFSET_DATE_TIME                        Date Time with Offset                                               '2011-12-03T10:15:30+01:00'
 * ISO_ZONED_DATE_TIME                         Zoned Date Time                                                     '2011-12-03T10:15:30+01:00[Europe/Paris]'
 * ISO_DATE_TIME                               Date and time with ZoneId                                           '2011-12-03T10:15:30+01:00[Europe/Paris]'
 * ISO_ORDINAL_DATE                            Year and day of year                                                '2012-337'
 * ISO_WEEK_DATE                               Year and Week                                                       '2012-W48-6'
 * ISO_INSTANT                                 Date and Time of an Instant                                         '2011-12-03T10:15:30Z'
 * RFC_1123_DATE_TIME                          RFC 1123 / RFC 822
 * <p>
 * Pattern
 * All letters 'A' to 'Z' and 'a' to 'z' are reserved as pattern letters. The following pattern letters are defined:
 * <p>
 * Symbol  Meaning                     Presentation      Examples
 * ------  -------                     ------------      -------
 * G       era                         text              AD; Anno Domini; A
 * u       year                        year              2004; 04
 * y       year-of-era                 year              2004; 04
 * D       day-of-year                 number            189
 * M/L     month-of-year               number/text       7; 07; Jul; July; J
 * d       day-of-month                number            10
 * <p>
 * Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
 * Y       week-based-year             year              1996; 96
 * w       week-of-week-based-year     number            27
 * W       week-of-month               number            4
 * E       day-of-week                 text              Tue; Tuesday; T
 * e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
 * F       week-of-month               number            3
 * <p>
 * a       am-pm-of-day                text              PM
 * h       clock-hour-of-am-pm (1-12)  number            12
 * K       hour-of-am-pm (0-11)        number            0
 * k       clock-hour-of-am-pm (1-24)  number            0
 * <p>
 * H       hour-of-day (0-23)          number            0
 * m       minute-of-hour              number            30
 * s       second-of-minute            number            55
 * S       fraction-of-second          fraction          978
 * A       milli-of-day                number            1234
 * n       nano-of-second              number            987654321
 * N       nano-of-day                 number            1234000000
 * <p>
 * V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
 * z       time-zone name              zone-name         Pacific Standard Time; PST
 * O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
 * X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
 * x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
 * Z       zone-offset                 offset-Z          +0000; -0800; -08:00;
 * <p>
 * p       pad next                    pad modifier      1
 * <p>
 * '       escape for text             delimiter
 * ''      single quote                literal           '
 * [       optional section start
 * ]       optional section end
 * #       reserved for future use
 * {       reserved for future use
 * }       reserved for future use
 */
public class DateUtils {
    public static String F19 = "yyyy-MM-dd HH:mm:ss";

    public static String F14 = "yyyyMMddHHmmss";

    public static String F10 = "yyyy-MM-dd";

    public static String F8 = "yyyyMMdd";

    public static String[] dataStringFormats = {F8, F10, F14, F19, "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm"};


    public void date() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse("yyyy-mm-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf2.format(date);
    }

    /**
     * LocalDate转String
     *
     * @param date
     * @return
     */
    public String LocalDateString(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = date.format(fmt);
        return dateStr;
    }

    /**
     * LocalDateTime转String
     */
    public void LocalDateTimeString() {
        LocalDateTime dateTime = LocalDateTime.now();

        //使用预定义实例来转换
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        String dateStr = dateTime.format(fmt);
        System.out.println("LocalDateTime转String[预定义]:" + dateStr);

        //使用pattern来转换
        //12小时制与24小时制输出由hh的大小写决定
        DateTimeFormatter fmt12 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS");
        String dateStr12 = dateTime.format(fmt12);
        System.out.println("LocalDateTime转String[pattern](12小时制):" + dateStr12);

        DateTimeFormatter fmt24 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr24 = dateTime.format(fmt24);
        System.out.println("LocalDateTime转String[pattern](24小时制):" + dateStr24);

        //如果想要给12小时制时间加上am/pm,这样子做：
        fmt12 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS a");
        dateStr12 = dateTime.format(fmt12);
        System.out.println("LocalDateTime转String[pattern](12小时制带am/pm):" + dateStr12);
    }

    /**
     * String转LocalDate和LocalDateTime
     */
    public void StringLocalDateLocalDateTime() {
        String str = "2017-11-21 14:41:06:612";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        LocalDate date = LocalDate.parse(str, fmt);
        LocalDateTime time = LocalDateTime.parse(str, fmt);
        System.out.println("date:" + date);
        System.out.println("time:" + time);
    }


    /**
     * 将字符串转换为long类型的值(不包含-符号)
     *
     * @param dateString 2016-10-12
     * @return 20161012
     */
    public static long stringToDateLong(String dateString) {
        String[] dates = dateString.split("-");
        return Long.valueOf(dates[0] + dates[1] + dates[2]);
    }

    /**
     * 将日期转化为默认的格式显示
     *
     * @param date Date实例
     * @return 2017-06-06
     */
    public static String dateToString(Date date) {
        return dateToString(date, F10);
    }

    /**
     * @param date   Date实例
     * @param format yyyy-MM-dd
     * @return 2017-06-06
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * 时间戳转默认字符串日期
     *
     * @param time 1496739850253
     * @return 2017-06-06
     */
    public static String longToString(long time) {
        return longToString(time, F10);
    }

    /**
     * 时间戳转字符串日期（格式可以自己选择）
     *
     * @param time   1496739850253
     * @param format yyyy-MM-dd
     * @return 2017-06-06
     */
    public static String longToString(long time, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date(time));
    }

    /**
     * 在指定日期上加上一定天数获得新的日期
     *
     * @param day    2016-06-06
     * @param addDay 10
     * @return 2016-06-16
     */
    public static String getNextDay(String day, int addDay) {
        Calendar calendar = getCalendar(day);
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        return getDateString(calendar);
    }

    /**
     * 获取当前时间（包含小时、分、秒）
     *
     * @return 2016-06-06 10:20:10
     */
    public static String getCurrTime() {
        return dateToString(new Date(), F19);
    }

    /**
     * 获取当前日期（不包含小时、分、秒）
     *
     * @return 2016-06-06
     */
    public static String getCurrDate() {
        return dateToString(new Date(), F10);
    }

    /**
     * 获取日期相距天数
     *
     * @param startDate 2016-06-06
     * @param endDate   2016-06-10
     * @return int 4
     */
    public static int getCompareDate(String startDate, String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(F10);
            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);
            long l = date2.getTime() - date1.getTime() + 1000;
            long d = l / (24 * 60 * 60 * 1000);
            return (int) d;
        } catch (ParseException e) {
        }
        return 0;
    }

    private static Calendar getCalendar(String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day.substring(0, 4)), Integer.parseInt(day.substring(5, 7)) - 1, Integer.parseInt(day.substring(8, 10)), 0, 0, 0);
        return cal;
    }

    /**
     * 当前时间几小时相差多少时间
     *
     * @param hour
     * @return
     * @since 2017年8月18日
     */
    public static Date getAddHourTime(int hour) {
        Calendar dalendar = Calendar.getInstance();
        dalendar.add(Calendar.HOUR_OF_DAY, hour);
        return dalendar.getTime();
    }

    /**
     * 将Calendar转换为日期字符串（字符串的格式：2018-04-21）
     *
     * @param cale
     * @return
     */
    public static String getDateString(Calendar cale) {
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
    }

    /**
     * Calendar转为指定格式的日期字符串
     *
     * @param cale
     * @param format
     * @return
     */
    public static String getDateString(Calendar cale, String format) {
        return dateToString(cale.getTime(), format);
    }

    /**
     * 计算两个日期之间相差的时间
     *
     * @param sDate
     * @param eDate
     * @return
     * @throws Exception
     */
    public static long substract(String sDate, String eDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = sdf.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (d2.getTime() - d1.getTime() + 1000000) / (3600 * 24 * 1000);
    }

    /**
     * 返回当前日期, 格式：yyyy-mm-dd 使用方法： Date.getToday();
     *
     * @return 2018-04-21
     */
    public static String getToday() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month < 10 ? "0" + month : month + "") + "-" + (day < 10 ? "0" + day : day + "");
    }

    /**
     * 字符串的日期格式的计算
     *
     * @param smdate 较大时结果为负数
     * @param bdate  较大时结果为正数
     * @return int
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取yyyy-MM-dd格式日期的所在星期数
     * 例如2018-04-21得到的结果是7
     *
     * @param dateStr
     * @return
     */
    public static int getWeekDay(String dateStr) {
        int week = -1;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = ft.parse(dateStr);
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                week = cal.get(Calendar.DAY_OF_WEEK);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    public static String intToDay(long day) {
        String dayStr = String.valueOf(day);
        return new StringBuilder().append(dayStr.substring(0, 4)).append("-").append(dayStr.substring(4, 6)).append("-").append(dayStr.substring(6, 8)).toString();
    }

    public static long dayToInt(String day) {
        return Long.parseLong(day.replaceAll("-", ""));
    }


    /**
     * 判断某一日期是星期几，星期天为第7天
     *
     * @param day
     * @return
     */
    public static String getDayOfWeekCh(String day) {
        int dayInt;
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(day.substring(0, 4)), Integer.parseInt(day.substring(5, 7)) - 1, Integer.parseInt(day.substring(8, 10)), 0, 0, 0);
        dayInt = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayInt == 0) {
            dayInt = 7;
        }
        return dayInt + "";
    }

    /**
     * 日期解析
     *
     * @param source
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String source, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取指定年月份的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String monthFirstDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取指定年月份的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String monthLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }


    /**
     * Date类型转LocalDate类型
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localPriceDate = instant.atZone(zoneId).toLocalDate();
        return localPriceDate;
    }

    /**
     * LocalDate类型转Date类型
     *
     * @param localDate
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }
}