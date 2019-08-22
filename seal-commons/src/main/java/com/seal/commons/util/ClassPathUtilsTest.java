package com.seal.commons.util;

import org.apache.commons.lang3.ClassPathUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 15:54
 * @description 处理类路径的一些工具类
 **/
public class ClassPathUtilsTest {
    public static void main(String[] args) {
        getQualifiedName();
    }

    /**
     * 返回一个由class包名+resourceName拼接的字符串
     */
    public static void getQualifiedName() {
        String fullPath = ClassPathUtils.toFullyQualifiedName(Integer.class, "");
        String path = ClassPathUtils.toFullyQualifiedPath(StringUtils.class, "StringUtils.properties");

        // java.lang.
        System.out.println(fullPath);
       // org/apache/commons/lang3/StringUtils.properties
        System.out.println(path);
        // fullPath = ClassPathUtils.toFullyQualifiedName(Integer.class.getPackage(), "Integer.value");
        fullPath = ClassPathUtils.toFullyQualifiedName(Integer.class, "Integer.value");
        // java.lang.Integer.value
        System.out.println(fullPath);
    }

}
