package com.seal.commons.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 16:12
 * @description 需要随机字符串的时候，它或许能帮上忙
 **/
public class RandomStringUtilsTest {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        // 随便随机一个字  所以有可能是乱码
        String random = RandomStringUtils.random(10);
        // 在指定范围内随机
        String randomChars = RandomStringUtils.random(3,'a','b','c','d','e');
        // 随便随机10个Ascii
        String randomAscii = RandomStringUtils.randomAscii(10);
        // 注意这里不是5到10内随机,而是随机一个长度的数字
        String randomNumeric = RandomStringUtils.randomNumeric(5,10);
        // 鸜𡛷𦎬𥼢牃𪾈
        System.out.println(random);
        // dac
        System.out.println(randomChars);
        // hpCQrtmUvi
        System.out.println(randomAscii);
        // 2580338
        System.out.println(randomNumeric);

    }
}
