package com.seal.commons.util;

import com.seal.commons.enums.ImagesTypeEnum;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 16:08
 * @description 辅助操作枚举的一些工具
 **/
public class EnumUtilsTest {

    public static void main(String[] args) {

        imagesTypeEnumTest();
    }

    /**
     * getEnum(Class enumClass, String enumName) 通过类返回一个枚举，可能返回空
     * getEnumList(Class enumClass) 通过类返回一个枚举集合
     * getEnumMap(Class enumClass) 通过类返回一个枚举map
     * isValidEnum(Class enumClass, String enumName) 验证enumName是否在枚举中，返回true false
     */
    public static void imagesTypeEnumTest() {
        ImagesTypeEnum imagesTypeEnum = EnumUtils.getEnum(ImagesTypeEnum.class, "JPG");
        System.out.println("imagesTypeEnum = " + imagesTypeEnum);
        System.out.println("--------------");

        List<ImagesTypeEnum> imagesTypeEnumList = EnumUtils.getEnumList(ImagesTypeEnum.class);
        imagesTypeEnumList.stream().forEach(
                imagesTypeEnum1 -> System.out.println("imagesTypeEnum1 = " + imagesTypeEnum1)
        );
        System.out.println("--------------");

        Map<String, ImagesTypeEnum> imagesTypeEnumMap = EnumUtils.getEnumMap(ImagesTypeEnum.class);
        imagesTypeEnumMap.forEach((k, v) -> System.out.println("key：" + k + ",value：" + v));
        System.out.println("-------------");

        boolean result = EnumUtils.isValidEnum(ImagesTypeEnum.class, "JPG");
        System.out.println("result = " + result);
        boolean result1 = EnumUtils.isValidEnum(ImagesTypeEnum.class, null);
        System.out.println("result1 = " + result1);

    }
}
