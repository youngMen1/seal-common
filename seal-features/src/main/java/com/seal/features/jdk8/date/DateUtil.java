package com.seal.features.jdk8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * JDK8日期
 * Instant：瞬时实例。
 * LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
 * LocalTime：本地时间，不包含日期。
 * LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 *
 * @author fengzhiqiang
 * @date-time 2020/6/16 9:32
 **/
public class DateUtil {
    public static void main(String[] args) {
        // 获取今天的日期
        getCurrentDate();
        // 获取年、月、日信息
        getDetailDate();
        // 处理特定日期
        handleSpecilDate();
        // 断两个日期是否相等
        compareDate();
        // 处理周期性的日期
        cycleDate();
        // 获取当前时间
        getCurrentTime();
        // 增加小时
        plusHours();
        // 如何计算一周后的日期
        nextWeek();
        // 计算一年前或一年后的日期
        minusDate();
        // Java 8的Clock时钟类
        clock();
        // 如何用Java判断日期是早于还是晚于另一个日期
        isBeforeOrIsAfter();
        // 获取特定时区下面的时间
        getZoneTime();
        // 使用 YearMonth类处理特定的日期
        checkCardExpiry();
        // 检查闰年
        isLeapYear();
        // 计算两个日期之间的天数和月数
        calcDateDays();
        // 包含时差信息的日期和时间
        zoneOffset();
        // 获取当前的时间戳
        getTimestamp();
        // 使用预定义的格式化工具去解析或格式化日期
        formateDate();
    }

    /**
     * 获取今天的日期
     */
    public static void getCurrentDate() {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("jdk8获取今天的日期(localDateTime):" + localDateTime);

        LocalDate localDate = LocalDate.now();
        System.out.println("jdk8获取今天的日期(localDate):" + localDate);

        System.out.println("jdk8获取今天的日期(localDateTime):" + LocalDateTime.of(localDate, LocalTime.MAX));

        // 这个是作为对比
        Date date = new Date();
        System.out.println("Date获取今天的日期:" + date);
    }

    /**
     * 获取年、月、日信息
     */
    public static void getDetailDate() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d  Month : %d  day : %d t %n", year, month, day);
    }

    /**
     * 处理特定日期
     */
    public static void handleSpecilDate() {
        LocalDate dateOfBirth = LocalDate.of(2020, 6, 16);
        System.out.println("特殊日期是 : " + dateOfBirth);
    }

    /**
     * 判断两个日期是否相等
     */
    public static void compareDate() {
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2020, 6, 16);

        if (date1.equals(today)) {
            System.out.printf("TODAY %s and DATE1 %s are same date %n", today, date1);
        }
    }

    /**
     * 处理周期性的日期
     */
    public static void cycleDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2020, 6, 16);

        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }

    /**
     * 获取当前时间
     */
    public static void getCurrentTime() {
        LocalTime time = LocalTime.now();
        System.out.println("现在是当地时间 : " + time);
    }

    /**
     * 增加小时
     */
    public static void plusHours() {
        LocalTime time = LocalTime.now();
        // 增加两小时
        LocalTime newTime = time.plusHours(2);
        System.out.println("2小时后的时间 : " + newTime);
    }

    /**
     * 如何计算一周后的日期
     */
    public static void nextWeek() {
        LocalDate today = LocalDate.now();
        // 使用变量赋值
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("今天是 : " + today);
        System.out.println("1周后的日期 : " + nextWeek);
    }


    /**
     * 计算一年前或一年后的日期
     */
    public static void minusDate() {
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("1年之前的日期 : " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("1年之后的日期 : " + nextYear);
    }

    /**
     * Java 8的Clock时钟类
     */
    public static void clock() {
        // 根据系统时间返回当前时间并设置为UTC。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // 根据系统时钟区域返回时间
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("defaultClock : " + defaultClock);
    }

    /**
     * 如何用Java判断日期是早于还是晚于另一个日期
     */
    public static void isBeforeOrIsAfter() {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = LocalDate.of(2020, 6, 15);
        if (tomorrow.isAfter(today)) {
            System.out.println("2020.6.15是今天(2020.6.16)后一天");
        }

        // 减去一天
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);

        if (yesterday.isBefore(today)) {
            System.out.println("2020.6.15是今天(2020.6.16)的前一天");
        }
    }

    /**
     * 获取特定时区下面的时间
     */
    public static void getZoneTime() {
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");

        LocalDateTime localtDateAndTime = LocalDateTime.now();

        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    /**
     * 使用 YearMonth类处理特定的日期
     */
    public static void checkCardExpiry() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("一年中的天数 %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("您的信用卡已过期 on %s %n", creditCardExpiry);
    }

    /**
     * 检查闰年
     */
    public static void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("今年是闰年");
        } else {
            System.out.println("今年不是闰年");
        }
    }

    /**
     * 计算两个日期之间的天数和月数
     */
    public static void calcDateDays() {
        LocalDate today = LocalDate.now();

        LocalDate java8Release = LocalDate.of(2020, Month.AUGUST, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);

        System.out.println("相差月数 : "
                + periodToNextJavaRelease.getMonths() + "相差天数:" + periodToNextJavaRelease.getDays());
    }

    /**
     * 包含时差信息的日期和时间
     */
    public static void zoneOffset() {
        LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Java中带时区偏移的日期和时间 : " + date);
    }


    /**
     * 获取当前的时间戳
     */
    public static void getTimestamp() {
        Instant timestamp = Instant.now();
        System.out.println("当前的时间戳: " + timestamp);
    }

    /**
     * 使用预定义的格式化工具去解析或格式化日期
     */
    public static void formateDate() {
        String dayAfterTommorrow = "20180210";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("从字符串生成的日期 %s is %s %n", dayAfterTommorrow, formatted);
    }
}
