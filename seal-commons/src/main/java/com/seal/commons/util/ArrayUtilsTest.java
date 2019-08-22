package com.seal.commons.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 15:35
 * @description ArrayUtils 学习
 * 用于对数组的操作，如添加、查找、删除、子数组、倒序、元素类型转换等
 **/
public class ArrayUtilsTest {
    public static void main(String[] args) {
        array();
        deepEqualsArray();
        toObject();


    }

    /**
     * 它提供了8中基本数据类型以及包装类以及各种类型的长度为0的空数组。
     * 所以以后需要长度为0的数组，可以不用new了，直接用这个即可
     */
    public static void array() {
        Integer[] inArr = new Integer[]{1, 2, 3};
        Integer[] inArr2 = new Integer[]{1, 2, 3};
        // 862547
        System.out.println(ArrayUtils.hashCode(inArr));
        // 862547
        System.out.println(ArrayUtils.hashCode(inArr2));

        inArr = new Integer[]{1, 2, 3};
        inArr2 = new Integer[]{1, 3, 3};
        // 862547
        System.out.println(ArrayUtils.hashCode(inArr));
        // 862584
        System.out.println(ArrayUtils.hashCode(inArr2));
    }

    /**
     * isEquals：该方法已经被废弃。取代的为java自己的java.util.Objects.deepEquals(Object, Object)
     */
    public static void deepEqualsArray() {
        Integer[] inArr = new Integer[]{1, 2, 3};
        Integer[] inArr2 = new Integer[]{1, 2, 3};
        // true
        System.out.println(Objects.deepEquals(inArr, inArr2));

        inArr = new Integer[]{1, 2, 3};
        inArr2 = new Integer[]{1, 3, 3};
        // false
        System.out.println(Objects.deepEquals(inArr, inArr2));
    }

    /**
     * toObject/toPrimitive：这两个方法很有用 可以实现比如int[]和Integer[]数组之间的互转
     */
    public static void toObject() {
        Integer[] inArr = new Integer[]{1, 2, 3};
        int[] ints = ArrayUtils.toPrimitive(inArr);
        Integer[] integers = ArrayUtils.toObject(ints);
        System.out.println(integers);
    }

    /**
     * toStringArray：同上。这个方法是将Object数组转换成String数组。
     * getLength、isSameLength：有时候建议使用。因为它是对null安全的。null的length为0
     */
    public static void toStringArray(){
        Integer[] inArr = new Integer[]{1, 2, 3};
        int[] ints = new int[]{1,2,3};
        String[] strings = ArrayUtils.toStringArray(inArr);
        //ArrayUtils.toStringArray(ints); //编译报错哟

       // Integer[] inArr = new Integer[]{1, 2, null};
        //String[] strings = ArrayUtils.toStringArray(inArr);

        //如果里面有null元素，会报错的，所以我们可以用下面这个方法 把null转成指定的值即可
       // String[] strings = ArrayUtils.toStringArray(inArr,"");
    }


}
