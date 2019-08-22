package com.seal.commons.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 16:18
 * @description
 * isAllBlank、isAllEmpty
 * 这些都不解释了。处理数组可变参数而已
 * isAllLowerCase、isAllUpperCase
 * 判断字符串所有字符是否都是大、小写
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

    /**
     * 这个方法有时候会很有用的。字符串在另外一个字符串里，出现第Ordinal次的位置
     * 对应的有：lastOrdinalIndexOf方法
     */
    public static void ordinalIndexOfTest() {
//        StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
//        StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
//        StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
//        StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
//        StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
//        StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
//        StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0 //空串永远访问0
//        StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0 //空串永远访问0
    }

    /**
     * 是否包含后面数组中的任意对象，返回true（和List里的containsAll有点像）
     */
    public static void containsAny() {
//        StringUtils.containsAny(null, *)                = false
//        StringUtils.containsAny("", *)                  = false
//        StringUtils.containsAny(*, null)                = false
//        StringUtils.containsAny(*, [])                  = false
//        StringUtils.containsAny("zzabyycdxx",['z','a']) = true
//        StringUtils.containsAny("zzabyycdxx",['b','y']) = true
//        StringUtils.containsAny("aba", ['z'])           = false
    }

    /**
     * 这个系列有的时候很有用，特别是下面的衍生方法：
     */
    public static void substringTest() {
        //从左边开始截取指定个数
        // public static String left(String str,int len)
        //从右边开始截取指定个数
        // public static String right(String str,int len)
        //从中间的指定位置开始截取  指定个数
        //  public static String mid(String str,int pos,int len)
    }

    /**
     * 默认使用空串Join
     * 自定义符号：特定字符串连接数组，很多情况下还是蛮实用，不用自己取拼字符串
     */
    public static void join() {
//        StringUtils.join(null)            = null
//        StringUtils.join([])              = ""
//        StringUtils.join([null])          = ""
//        StringUtils.join(["a", "b", "c"]) = "abc"
//        StringUtils.join([null, "", "a"]) = "a"
//        StringUtils.join(null, *)               = null
//        StringUtils.join([], *)                 = ""
//        StringUtils.join([null], *)             = ""
//        StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
//        StringUtils.join(["a", "b", "c"], null) = "abc"
//        StringUtils.join([null, "", "a"], ';')  = ";;a" //注意这里和上面的区别

    }

    /**
     * 删除空格 这个方法还挺管用的。比trim给力
     */
    public static void deleteWhitespaceTest() {
//        StringUtils.deleteWhitespace(null)         = null
//        StringUtils.deleteWhitespace("")           = ""
//        StringUtils.deleteWhitespace("abc")        = "abc"
//        StringUtils.deleteWhitespace("   ab  c  ") = "abc"

    }

    /**
     * 删除以特定字符串开头的字符串，如果没有的话，就不删除。
     * 这个方法有时候很管用啊
     */
    public static void removeStartTest() {
//        StringUtils.removeStart(null, *)      = null
//        StringUtils.removeStart("", *)        = ""
//        StringUtils.removeStart(*, null)      = *
//        StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
//        StringUtils.removeStart("domain.com", "www.")       = "domain.com"
//        StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com" //注意这个结果哟  并没有删除任何东西
//        StringUtils.removeStart("abc", "")    = "abc"
    }

    /**
     * 这个方法还是蛮管用的。对于生成统一长度的字符串的时候。
     * 比如生成订单号，为了保证长度统一，可以右边自动用指定的字符补全至指定长度
     * 对应的：leftPad 左边自动补全
     */
    public static void rightPadTest() {
//        StringUtils.rightPad(null, *, *)     = null
//        StringUtils.rightPad("", 3, 'z')     = "zzz"
//        StringUtils.rightPad("bat", 3, 'z')  = "bat"
//        StringUtils.rightPad("bat", 5, 'z')  = "batzz"
//        StringUtils.rightPad("bat", 1, 'z')  = "bat"
//        StringUtils.rightPad("bat", -1, 'z') = "bat"
    }

    /**
     * 将字符串扩展到指定的长度。把主体放在中间，两边自动用空串补齐
     */
    public static void centerTest() {
//        StringUtils.center(null, *)   = null
//        StringUtils.center("", 4)     = "    "
//        StringUtils.center("ab", -1)  = "ab"
//        StringUtils.center("ab", 4)   = " ab "
//        StringUtils.center("abcd", 2) = "abcd"
//        StringUtils.center("a", 4)    = " a  "

        /**
         * 去返大小写 大变小 小变大
         * public static String capitalize(String str)、uncapitalize
         * 首字母大、小写
         * public static String swapCase(String str)
         */
//        StringUtils.swapCase(null)                 = null
//        StringUtils.swapCase("")                   = ""
//        StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
    }

    /**
     * 判断字符串是否全由字母组成 （只要存在汉字、中文、数字都为false）
     */
    public static void sAlphaTest() {
//        StringUtils.isAlpha(null)   = false
//        StringUtils.isAlpha("")     = false
//        StringUtils.isAlpha("  ")   = false
//        StringUtils.isAlpha("abc")  = true
//        StringUtils.isAlpha("ab2c") = false
//        StringUtils.isAlpha("ab-c") = false

        /**
         * 字符串翻转
         */
//        StringUtils.reverse(null)  = null
//        StringUtils.reverse("")    = ""
//        StringUtils.reverse("bat") = "tab"
    }

    /**
     * 缩略字符串，省略号要占三位。maxWith小于3位会报错。
     * 这个在大篇幅需要显示的时候，很管用有木有
     */
    public static void abbreviateTest() {
//        StringUtils.abbreviate(null, *)      = null
//        StringUtils.abbreviate("", 4)        = ""
//        StringUtils.abbreviate("abcdefg", 6) = "abc..."
//        StringUtils.abbreviate("abcdefg", 7) = "abcdefg"
//        StringUtils.abbreviate("abcdefg", 8) = "abcdefg"
//        StringUtils.abbreviate("abcdefg", 4) = "a..."
//        StringUtils.abbreviate("abcdefg", 3) = IllegalArgumentException
    }

    /**
     * 包装，用后面的字符串对前面的字符串进行包装
     * 其实相当于前后拼了相同的串
     */
    public static void wrapTest() {
//        StringUtils.wrap(null, *)        = null
//        StringUtils.wrap("", *)          = ""
//        StringUtils.wrap("ab", '\0')     = "ab"
//        StringUtils.wrap("ab", 'x')      = "xabx"
//        StringUtils.wrap("ab", '\'')     = "'ab'"
//        StringUtils.wrap("\"ab\"", '\"') = "\"\"ab\"\""
    }


}
