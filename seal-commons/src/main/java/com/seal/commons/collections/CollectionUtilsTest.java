package com.seal.commons.collections;

import com.sun.corba.se.impl.orb.ParserTable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 17:58
 * @description CollectionUtils的addIgnoreNull()方法可用于确保只有非空(null)值被添加到集合中。
 * Apache Commons Collections库的CollectionUtils类提供各种实用方法，用于覆盖广泛用例的常见操作。
 * 它有助于避免编写样板代码。 这个库在jdk 8之前是非常有用的，但现在Java 8的Stream API提供了类似的功能。
 **/
public class CollectionUtilsTest {
    public static void main(String[] args) {
        // 检查是否为空元素
        addIgnoreNullTest();
        // 合并两个排序列表
        collateTest();
        // 转换列表
        collateTransformTest();
        // 使用filter()方法过滤列表
        filterTest();

    }

    /**
     * CollectionUtils的addIgnoreNull()方法可用于确保只有非空(null)值被添加到集合中
     */
    public static void addIgnoreNullTest() {
        List<String> list = new LinkedList<String>();
        CollectionUtils.addIgnoreNull(list, null);
        CollectionUtils.addIgnoreNull(list, "a");
        System.out.println(list);
        if (list.contains(null)) {
            System.out.println("Null value is present");
        } else {
            System.out.println("Null value is not present");
        }
    }


    /**
     * CollectionUtils的collate()方法可用于合并两个已排序的列表。
     */
    public static void collateTest() {
        List<String> sortedList1 = Arrays.asList("A", "C", "E");
        List<String> sortedList2 = Arrays.asList("B", "D", "F");
        List<String> mergedList = CollectionUtils.collate(sortedList1, sortedList2);
        System.out.println(mergedList);
    }

    /**
     * CollectionUtils的collect()方法可用于将一种类型的对象列表转换为不同类型的对象列表。
     */
    public static void collateTransformTest() {
        List<String> stringList = Arrays.asList("1", "2", "3");

        List<Integer> integerList = (List<Integer>) CollectionUtils.collect(stringList,
                new Transformer<String, Integer>() {
                    @Override
                    public Integer transform(String input) {
                        return Integer.parseInt(input);
                    }
                });
        System.out.println(integerList);
    }

    /**
     * CollectionUtils的filter()方法可用于过滤列表以移除不满足由谓词传递提供的条件的对象。
     */
    public static void filterTest() {
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        System.out.println("Original List: " + integerList);
        CollectionUtils.filter(integerList, new Predicate<Integer>() {

            @Override
            public boolean evaluate(Integer input) {
                if (input.intValue() % 2 == 0) {
                    return true;
                }
                return false;
            }
        });
        System.out.println("Filtered List (Even numbers): " + integerList);
    }

}
