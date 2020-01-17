package com.seal.util.util;

import java.util.Date;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/5 18:14
 * @description
 **/
public class WordUpperLow {
    public static void main(String[] args) {
        if (upToLow("HHHHHHHHH").equals(upToLow("hhhhhhhhh"))) {
            System.out.println("11111111111");
        }
    }

    /**
     * 方法一
     *
     * @param str
     * @return
     */
    public static String upToLow(String str) {
        // 根据 char 的工具类 Character
        char[] chars = str.toCharArray();
        for (int i = 0, length = chars.length; i < length; i++) {
            char c = chars[i];
            //判断字母是不是大写，如果是大写变为小写
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
                continue;
            }
        }
        String str1 = new String(chars);
        return str1;
    }

    /**
     * 方法二
     * 通过ASCII码判断字母大小写 ASCII在65-90之间是大写，97-122是小
     * 大转小加32 小转大减去32
     *
     * @param string
     * @return
     */
    private String upToLowToUp(String string) {
        byte[] bytes = string.getBytes();
        for (int i = 0, length = bytes.length; i < length; i++) {
            //如果ASCII在65-90之间为大写，加上32变为小写
            if (bytes[i] >= 65 && bytes[i] <= 90) {
                bytes[i] += 32;
                //如果ASCII在97-122之间为小写，减去32变为大写
            } else if (bytes[i] >= 97 && bytes[i] <= 122) {
                bytes[i] -= 32;
            }
        }
        String str2 = new String(bytes);
        System.err.println(str2);
        return str2;
    }

    /**
     * 小写转大写
     *
     * @param c
     * @return
     */
    public char toUpper(char c) {
        return c >= 65 && c <= 90 ? c : (char) (c - 32);
    }

    /**
     * 大写转小写
     *
     * @param c
     * @return
     */
    public char toLower(char c) {
        return c >= 97 && c <= 122 ? c : (char) (c + 32);
    }


    /**
     * 是否是大写
     *
     * @param c
     * @return
     */
    public boolean isUpperCase(char c) {
        // Character.isUpperCase(c);
        return c >= 65 && c <= 90;
    }


    /**
     * 是否是小写
     *
     * @param c
     * @return
     */
    public boolean isLowerCase(char c) {
//        Character.isLowerCase(c);
        return c >= 97 && c <= 122;
    }

}
