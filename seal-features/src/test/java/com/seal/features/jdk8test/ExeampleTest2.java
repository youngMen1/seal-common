package com.seal.features.jdk8test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengzhiqiang
 * @date-time 2020/5/8 11:49
 **/
public class ExeampleTest2 {


    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");


    @Test
    public void test31() {
        final List<String> uppercaseNames = new ArrayList<String>();
        for (String name : friends) {
            uppercaseNames.add(name.toUpperCase());
        }
        System.out.println(uppercaseNames);
    }

    @Test
    public void test32() {
        final List<String> uppercaseNames = new ArrayList<String>();
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
        System.out.println(uppercaseNames);
    }

    /**
     * Stream的map方法可以用来将输入序列转化成一个输出的序列
     * map方法把lambda表达式的运行结果收齐起来，返回一个结果集
     */
    @Test
    public void test33() {
        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.print(name + " "));
        System.out.println();
    }

    /**
     *
     *
     */
    @Test
    public void test34() {
        friends.stream()
                .map(name -> name.length())
                .forEach(count -> System.out.print(count + " "));
    }

    /**
     *
     */
    @Test
    public void test35() {
        friends.stream()
                .map(String::length)
                .forEach(count -> System.out.print(count + " "));
    }

}
