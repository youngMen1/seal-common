package com.seal.commons.collections;

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
        // 检查子列表
        isSubCollectionTest();
        // 检查相交
        intersectionTest();
        // 求差集
        subtractTest();
        // 求联合集
        unionTest();

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

    /**
     * 检查子列表
     * CollectionUtils的isSubCollection()方法可用于检查集合是否包含给定集合。
     */
    public static void isSubCollectionTest() {
        //checking inclusion
        List<String> list1 = Arrays.asList("A", "A", "A", "C", "B", "B");
        List<String> list2 = Arrays.asList("A", "A", "B", "B");

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        // true
        System.out.println("Is List 2 contained in List 1: "
                + CollectionUtils.isSubCollection(list2, list1));
    }

    /**
     * 检查相交
     * CollectionUtils的intersection()方法可用于获取两个集合(交集)之间的公共对象部分。
     */
    public static void intersectionTest(){

        //checking inclusion
        List<String> list1 = Arrays.asList("A","A","A","C","B","B");
        List<String> list2 = Arrays.asList("A","A","B","B");

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        // [A, A, B, B]
        System.out.println("Commons Objects of List 1 and List 2: "
                + CollectionUtils.intersection(list1, list2));
    }

    /**
     * 求差集CollectionUtils的subtract()方法可用于通过从其他集合中减去一个集合的对象来获取新集合。
     * 参数
     * a - 要从中减去的集合，不能为null。
     * b - 要减去的集合，不能为null。
     */
    private static void subtractTest() {
        //checking inclusion
        List<String> list1 = Arrays.asList("A","A","A","C","B","B");
        List<String> list2 = Arrays.asList("A","A","B","B");

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        // [A, C]
        System.out.println("List 1 - List 2: "
                + CollectionUtils.subtract(list1, list2));

    }

    /**
     * CollectionUtils的union()方法可用于获取两个集合的联合。
     */
    private static void unionTest() {
        //checking inclusion
        List<String> list1 = Arrays.asList("A","A","A","C","B","B");
        List<String> list2 = Arrays.asList("A","A","B","B");

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        // [A, A, A, B, B, C]
        System.out.println("Union of List 1 and List 2: "
                + CollectionUtils.union(list1, list2));

    }

}
