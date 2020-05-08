package com.seal.features.jdk8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * MapReduce map（映射）和reduce(归约，化简）是数学上两个很基础的概念
 * @author fengzhiqiang
 * @date-time 2020/5/8 14:00
 **/
public class ExeampleTest6 {


    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");


    @Test
    public void test81() {
        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(name -> name.length())
                .sum());
    }

    @Test
    public void test82() {
        final Optional<String> aLongName = friends.stream().reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name -> System.out.println(String.format("A longest name: %s", name)));
    }

    @Test
    public void test83() {
        final String steveOrLonger = friends.stream().reduce("Steve", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        System.out.println(steveOrLonger);
    }

    @Test
    public void test84() {
        for (String name : friends) {
            System.out.print(name + ", ");
        }
        System.out.println();

        for (int i = 0; i < friends.size() - 1; i++) {
            System.out.print(friends.get(i) + ", ");
        }

        if (friends.size() > 0) {
            System.out.println(friends.get(friends.size() - 1));
        }

        System.out.println(String.join(", ", friends));

        System.out.println(friends.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
    }

}
