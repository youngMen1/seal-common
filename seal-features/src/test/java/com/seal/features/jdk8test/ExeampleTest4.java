package com.seal.features.jdk8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * lambda表达式的重用
 *
 * @author fengzhiqiang
 * @date-time 2020/5/8 13:47
 **/
public class ExeampleTest4 {

    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
    private final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
    private final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");


    /**
     * lambda表达式带来的冗余
     */
    @Test
    public void test51() {
        final long count1 = friends.stream().filter(name -> name.startsWith("N")).count();
        final long count2 = editors.stream().filter(name -> name.startsWith("N")).count();
        final long count3 = comrades.stream().filter(name -> name.startsWith("N")).count();
        System.out.println(count1 + " " + count2 + " " + count3);
    }

    /**
     * 重用
     */
    @Test
    public void test52() {
        final Predicate<String> startsWith = name -> name.startsWith("N");
        final long count1 = friends.stream().filter(startsWith).count();
        final long count2 = editors.stream().filter(startsWith).count();
        final long count3 = comrades.stream().filter(startsWith).count();
        System.out.println(count1 + " " + count2 + " " + count3);
    }


}
