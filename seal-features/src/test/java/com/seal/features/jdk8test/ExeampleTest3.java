package com.seal.features.jdk8test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在集合中查找元素
 * @author fengzhiqiang
 * @date-time 2020/5/8 11:54
 **/
public class ExeampleTest3 {
    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    @Test
    public void test41() {
        final List<String> startsWith = new ArrayList<String>();
        for (String name : friends) {
            if (name.startsWith("N")) {
                startsWith.add(name);
            }
        }
        System.out.println(startsWith);
    }

    /**
     * filter方法接收一个返回布尔值的lambda表达式。
     * 如果表达式结果为true，运行上下文中的那个元素就会被添加到结果集中;
     * 如果不是，就跳过它。最终返回的是一个Steam，它里面只包含那些表达式返回true的元素。
     * 最后我们用一个collect方法把这个集合转化成一个列表
     */
    @Test
    public void test42() {
        final List<String> startsWith =
                friends.stream()
                        .filter(name -> name.startsWith("N"))
                        .collect(Collectors.toList());
        System.out.println(startsWith);
    }

}
