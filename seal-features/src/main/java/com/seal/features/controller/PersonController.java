package com.seal.features.controller;

import com.seal.features.entity.Person;
import com.seal.features.entity.Person2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 15:00
 * @description
 **/
public class PersonController {

    public static void main(String[] args) {
        List<Person2> peoples = Arrays.asList(
                new Person2("java", "22"),
                new Person2("js", "35"),
                new Person2("js", "35"),
                new Person2("js", "35"),
                new Person2("css", "31")
        );

        Person2 result1 = peoples.stream()
                .filter(p -> "java".equals(p.getName()))
                .findAny()
                .orElse(null);
        System.out.println(result1);

        Person2 result2 = peoples.stream()
                .filter(p -> "spring".equals(p.getName()))
                .findAny()
                .orElse(null);
        System.out.println(result2);

        Person2 result3 = peoples.stream()
                .filter((p) -> "java".equals(p.getName()) && "22".equals(p.getAge()))
                .findAny()
                .orElse(null);
        System.out.println(result3);

        // 使用map收集
        String name = peoples.stream()
                .filter(p -> "js".equals(p.getName()))
                .map(Person2::getName)
                .findAny()
                .orElse("");
        System.out.println(name);
        System.out.println("---------");

        // 使用personList收集
        List<Person2> personList = peoples.stream()
                .filter(p -> "js".equals(p.getName()))
                .collect(Collectors.toList());

        System.out.println(personList);
        System.out.println("---------");

        List<String> names = peoples.stream()
                .map(Person2::getName)
                .collect(Collectors.toList());
        names.forEach(System.out::println);
    }
}
