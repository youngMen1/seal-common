package com.seal.commons.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 16:18
 * @description
 **/
public class StringUtilsTest {
    public static void main(String[] args) {
        isEmpty();
        isAnyEmpty();
        trimTest();
    }

    /**
     * 这个可能用得是非常多的，null和空串都被定义为empty了哟
     * isBlank 相当于深度的isEmpty
     */
    public static void isEmpty() {
        // true
        boolean boolean1 = StringUtils.isEmpty(null);
        System.out.println(boolean1);
        // true
        boolean boolean2 = StringUtils.isEmpty("");
        System.out.println(boolean2);
        // false  //注意这里是false
        boolean boolean3 = StringUtils.isEmpty(" ");
        System.out.println(boolean3);
        // false
        boolean boolean4 = StringUtils.isEmpty("bob");
        System.out.println(boolean4);
        // false
        boolean boolean5 = StringUtils.isEmpty("  bob  ");
        System.out.println(boolean5);
    }

    /**
     * 任意一个参数为空的话，返回true。如果这些参数都不为空的话返回false。在写一些判断条件的时候，这个方法还是很实用的。
     */
    public static void isAnyEmpty() {
        System.out.println("---------------------------------");
        // false
        boolean boolean1 = StringUtils.isAnyEmpty(null);
        System.out.println(boolean1);

        // true
        boolean boolean2 = StringUtils.isAnyEmpty(null, "foo");
        System.out.println(boolean2);

        // true
        boolean boolean3 = StringUtils.isAnyEmpty("bob", "");
        System.out.println(boolean3);

        // true
        boolean boolean4 = StringUtils.isAnyEmpty("  bob  ", null);
        System.out.println(boolean4);

        // false  //注意这个是false哦
        boolean boolean5 = StringUtils.isAnyEmpty(" ", "bar");
        System.out.println(boolean5);

        // false
        boolean boolean6 = StringUtils.isAnyEmpty("foo", "bar");
        System.out.println(boolean6);
    }

    /**
     * 移除字符串两端的空字符串，制表符char <= 32如：\n \t 如果为null返回null
     */
    public static void trimTest() {
        String s1 = StringUtils.trim(null);
        System.out.println(s1);
        String s2 = StringUtils.trim("");
        System.out.println(s2);
        String s3 = StringUtils.trim("     ");
        System.out.println(s3);
        String s4 = StringUtils.trim("abc");
        System.out.println(s4);
        String s5 = StringUtils.trim("    abc    ");
        System.out.println(s5);
    }
}
