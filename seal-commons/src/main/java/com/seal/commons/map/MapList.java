package com.seal.commons.map;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/23 17:16
 **/
public class MapList {

    public static void main(String[] args) {
        // 大到小
        // TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());、
        // 小到大
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");
        // {5=val, 4=val, 3=val, 2=val, 1=val}
        System.out.println(map);


        Map<DayOfWeek, String> map2 = new EnumMap<>(DayOfWeek.class);
        map2.put(DayOfWeek.MONDAY, "星期一");
        map2.put(DayOfWeek.TUESDAY, "星期二");
        map2.put(DayOfWeek.WEDNESDAY, "星期三");
        map2.put(DayOfWeek.THURSDAY, "星期四");
        map2.put(DayOfWeek.FRIDAY, "星期五");
        map2.put(DayOfWeek.SATURDAY, "星期六");
        map2.put(DayOfWeek.SUNDAY, "星期日");
        System.out.println(map2);
        System.out.println(map2.get(DayOfWeek.MONDAY));
    }
}
