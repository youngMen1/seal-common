package com.seal.features.common;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Java8中处理时间和日期的类
 *
 * Instant：瞬时实例。
 * LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
 * LocalTime：本地时间，不包含日期。
 * LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 *
 * @author zhiqiang.feng
 * @date-time 2020/1/16 17:04
 **/
public class DateUtils8 {

    public static void main(String[] args) {
        // 处理日期 LocalDate
        // localDateTest();
        // 处理日期 LocalTime
        // localTimeTest();
        // 处理日期 LocalDateTime
        localDateTimeTest();
        System.out.println(transDateToLocalDate(new Date()));
    }

    /**
     * 处理日期 LocalDate
     */
    @Test
    public static void localDateTest() {
        // 获取当前日期   2020-06-16
        LocalDate today = LocalDate.now();
        System.out.println(today);

        // 构造日期   2020-06-16
        LocalDate today2 = LocalDate.of(2020, 6, 16);
        System.out.println(today2);

        // 构造日期   2017-02-22    字符串严格按照yyyy-MM-dd
        LocalDate today3 = LocalDate.parse("2020-06-16");
        System.out.println(today3);

        // 本月第一天  2020-06-01
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        // 本月第二天  2020-06-02   第n天
        LocalDate secondDayOfMonth = today.withDayOfMonth(2);
        System.out.println(secondDayOfMonth);

        // 本月最后一天 2020-06-30  方便解决任何年份的二月份多少天
        LocalDate lastDayOfMonth = today3.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfMonth);

        // 获取2020年12月的第一个周一   2020-12-07
        LocalDate firstDayOf201712 = LocalDate.parse("2020-12-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstDayOf201712);
    }

    /**
     * 处理时间  LocalTime
     */
    @Test
    public static void localTimeTest() {
        //获取当前时间  含有毫秒值  17:18:41.571
        LocalTime now = LocalTime.now();
        System.out.println(now);

        //获取当前时间   去掉毫秒值   17:45:41
        LocalTime now1 = LocalTime.now().withNano(0);
        System.out.println(now1);
        //00:46:46.651  提供了把时分秒都设为0的方法
        LocalTime now2 = LocalTime.now().withHour(0);
        System.out.println(now2);

        //构造时间  00:20:55
        LocalTime time1 = LocalTime.of(0, 20, 55);
        System.out.println(time1);
        //构造时间  05:43:22
        LocalTime time2 = LocalTime.parse("05:43:22");
        System.out.println(time2);

        //标准时间 2017-11-06T17:53:15.930
        LocalDateTime lt = LocalDateTime.now();
        System.out.println(lt);
    }

    /**
     * 处理时间  LocalDateTime
     */
    @Test
    public static void localDateTimeTest() {
        LocalDateTime time = LocalDateTime.now();
        // 字符串表示
        System.out.println(time.toString());
        // 获取时间(LocalTime)
        System.out.println(time.toLocalTime());
        // 获取日期(LocalDate)
        System.out.println(time.toLocalDate());
        // 获取当前时间月份的第几天
        System.out.println(time.getDayOfMonth());
        // 获取当前周的第几天
        System.out.println(time.getDayOfWeek());
        // 获取当前时间在该年属于第几天
        System.out.println(time.getDayOfYear());
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getMonthValue());
        System.out.println(time.getMonth());
        System.out.println("-----------------------------------");
        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        System.out.println(time.format(formatter));
        // 格式化输出
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(time.format(formatter2));

        // 格式化输出
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(time.format(formatter3));

        // 构造时间
        LocalDateTime startTime = LocalDateTime.of(2018, 1, 1, 20, 31, 20);
        LocalDateTime endTime = LocalDateTime.of(2018, 1, 3, 20, 31, 20);
        // 比较时间
        System.out.println(time.isAfter(startTime));
        System.out.println(time.isBefore(endTime));

        // 时间运算，相加相减
        System.out.println(time.plusYears(2)); // 加2年
        System.out.println(time.plusDays(2)); // 加两天
        System.out.println(time.minusYears(2)); // 减两年
        System.out.println(time.minusDays(2)); // 减两天

        // 获取毫秒数(使用Instant)
        System.out.println(time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        // 获取秒数(使用Instant)
        System.out.println(time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param time
     * @param format
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }


    /**
     * Date -> LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate transDateToLocalDate(Date date) {
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate->Date
     *
     * @param localDate
     * @return
     */
    public static Date transLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime transDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime
     * @return
     */
    private static Date transLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date -> LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime transDateToLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * LocalTime -> Date
     *
     * @param localTime
     * @return
     */
    private static Date transLocalTimeToDate(LocalTime localTime) {
        return Date.from(LocalDateTime.of(LocalDate.now(), localTime)
                .atZone(ZoneId.systemDefault()).toInstant());
    }
}
