package com.seal.guava.comparator;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import com.google.common.collect.Comparators;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 16:10
 * @description
 **/
public class Jdk8StringComparatorTest {
    public static void main(String[] args) {
        ArrayList<String> names = Lists.newArrayList("Ram", "Shyam", "Mohan", "Sohan",
                "Ramesh", "Suresh", "Naresh", "Mahesh", null, "", "Vikas", "Deepak");
        Comparator<String> comparator =
                Comparator.<String, Integer>comparing((str) -> {
                    //这是Java8的Optional
                    return Optional.ofNullable(str).orElse("").length();
                })
                        .thenComparing(Comparator.nullsFirst(Comparator.naturalOrder()));
        names.sort(comparator);
        System.out.println(names);
        System.out.println("最大的四个元素：" + names.stream().collect(Comparators.greatest(4, comparator)));
        System.out.println("最小的四个元素：" + names.stream().collect(Comparators.least(4, comparator)));
        System.out.println("最大的元素：" + names.stream().max(comparator).get());
        // 遗憾的是，min内部使用Optional.of，null元素会抛空指针异常
        System.out.println("最小的元素：" + names.stream().min(comparator).get());
    }
}
