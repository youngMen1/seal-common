package com.seal.guava.collections;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/2 17:46
 * @description Google Collections一览
 * 显然一篇博文不能深入地覆盖Google Collections的方方面面，所以我决定把时间放在我日常编码中使用到的基础且不失强大的特性上
 * https://www.oschina.net/translate/beautiful-code-with-google-collections-guava-and-static-imports-part-1
 **/
public class CollectionsTestUtils {

    Map<String, Map<Long, List<String>>> map = new HashMap<String, Map<Long, List<String>>>();

    Map<String, Map<Long, List<String>>> getMap = Maps.newHashMap();

    public static void main(String[] args) {
        setList();
        compareTo();
    }

    /**
     *
     */
    private static void createCollections() {
        // Map<String, Map<Long, List<String>>> map = new HashMap<String, Map<Long, List<String>>>();
        Map<String, Map<Long, List<String>>> setMap = Maps.newHashMap();
        List list = Lists.newArrayList();
        HashSet set = Sets.newHashSet();
    }

    /**
     * 不可变集合
     */
    private static void setList() {
        /*List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");*/

        ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
        System.out.println("guava:" + of);
    }

    /**
     * 比较两个基本类型
     */
    private static void compareTo() {
        int a = 10;
        int b = 5;
        int compareTo = Integer.valueOf(a).compareTo(Integer.valueOf(b));
        System.out.println(compareTo);

        // Guava
        int compare = Ints.compare(a, b);
        System.out.println("guava:" + compare);
    }

}
