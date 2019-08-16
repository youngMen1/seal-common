package com.seal.features.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/12/11 10:04
 * @description TODO
 **/
public class ItemController {

    public static void main(String[] args) {
        test2();
    }

    private static void test() {
        //3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);

    }


    private static void test2() {
        //3 apple, 2 banana, others 1
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
    }

    private static void test3() {
        //1.分组计数
    /*    List<Student> list1= Arrays.asList(
                new Student(1,"one","zhao"),new Student(2,"one","qian"),new Student(3,"two","sun"));
        //1.1根据某个属性分组计数
        Map<String,Long> result1=list1.stream().collect(Collectors.groupingBy(Student::getGroupId,Collectors.counting()));
        System.out.println(result1);
        //1.2根据整个实体对象分组计数,当其为String时常使用
        Map<Student,Long> result2=list1.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result2);
        //1.3根据分组的key值对结果进行排序、放进另一个map中并输出
        Map<String,Long> xMap=new HashMap<>();
        result1.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByKey().reversed()) //reversed不生效
                .forEachOrdered(x->xMap.put(x.getKey(),x.getValue()));
        System.out.println(xMap);

        //2.分组，并统计其中一个属性值得sum或者avg:id总和
        Map<String,Integer> result3=list1.stream().collect(
                Collectors.groupingBy(Student::getGroupId,Collectors.summingInt(Student::getId))
        );
        System.out.println(result3);
*/
    }
}



