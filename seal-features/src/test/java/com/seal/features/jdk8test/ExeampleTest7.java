package com.seal.features.jdk8test;

import org.junit.Test;

/**
 * 字符串及方法引用
 * @author fengzhiqiang
 * @date-time 2020/5/8 14:07
 **/
public class ExeampleTest7 {
    /**
     * 字符串及方法引用
     */
    @Test
    public void test9() {
        final String str = "w00t";
        str.chars().forEach(ch -> System.out.println(ch));

        str.chars().forEach(System.out::println);

        str.chars()
                .mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);

        str.chars()
                .filter(ch -> Character.isDigit(ch))
                .forEach(ch -> printChar(ch));

    }

    private static void printChar(int aChar) {
        System.out.println((char) (aChar));
    }

}
