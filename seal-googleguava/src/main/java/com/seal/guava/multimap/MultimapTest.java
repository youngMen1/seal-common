package com.seal.guava.multimap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.seal.guava.entity.StudentScore;

/**
 * Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>
 *
 * 为了解决Map<K, List>或者Map<K, Set>这样复杂的、嵌套的集合类型数据结构，
 * 避免麻烦的操作方式，项目中引入了Multimap集合类型。
 * 下面介绍几种常用的实现类型: HashMultiMap、LinkedHashMultimap、TreeMultimap，
 * 读者可以比对输出结果，观察到三者实现的差别，选择适合的结构使用。
 *
 * @author fengzhiqiang
 * @date 2021/5/14 14:58
 **/
public class MultimapTest {
    public static void main(String[] args) {
        testArrayListMultimap();
        System.out.println("----------------------------");
        testHashMultimap();
        System.out.println("----------------------------");
        testLinkedHashMultimap();
    }

    /**
     * LinkedHashMultimap类操作方法与HashMultimap类一致，
     * 唯一的区别是LinkedHashMultimap保存了记录的插入顺序，循环遍历时
     * 先放入Multimap中的数据先输出。
     * <p>
     * 注意：这个顺序对key／value都有效
     */
    private static void testLinkedHashMultimap() {

        Multimap<Integer, Integer> map = LinkedHashMultimap.create();

        map.put(4, 2);
        map.put(4, 7);
        map.put(1, 4);
        map.put(1, 5);
        map.put(1, 3);
        map.put(2, 3);
        map.put(2, 9);
        map.put(2, 7);
        map.put(4, 5);
        System.out.println(map.toString());

        /*
         *
         * 输出结果：
         * {4=[2, 7, 5], 1=[4, 5, 3], 2=[3, 9, 7]}
         *
         */
    }

    /**
     * Map<K, List>
     * <p>
     * ArrayListMultimap.create() ==> Map<String, List<StudentScore>>
     */
    public static void testArrayListMultimap() {
        Multimap<String, StudentScore> scoreMultimap = ArrayListMultimap.create();
        for (int i = 10; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.setCourseId(1001 + i);
            studentScore.setScore(100 - i);
            scoreMultimap.put("peida", studentScore);
        }
        System.out.println(scoreMultimap.toString());
        System.out.println("scoreMultimap:" + scoreMultimap.size());
        System.out.println("scoreMultimap:" + scoreMultimap.keys());
    }

    /**
     * key重复时，相同key的key-value pair 的value值是放在同一个数组中，相同的value会去重:
     * <p>
     * HashMultimap.create() ==> Map<K, Set>
     */
    public static void testHashMultimap() {
        Multimap<Integer, Integer> map = HashMultimap.create();
        map.put(4, 2);
        map.put(4, 7);
        map.put(1, 4);
        map.put(1, 5);
        map.put(1, 3);
        map.put(2, 3);
        map.put(2, 9);
        map.put(2, 7);
        map.put(4, 5);
        System.out.println(map.toString());

        /*
         * 输出结果：
         * {1=[4, 5, 3], 2=[9, 3, 7], 4=[5, 2, 7]}
         *
         */
    }
}
