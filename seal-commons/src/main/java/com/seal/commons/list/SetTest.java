package com.seal.commons.list;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author fengzhiqiang
 * @date-time 2020/4/23 16:09
 **/
public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("1");
        set.add("1");
        // set:[1]
        // size:1
        System.out.println("set:" + set.toString());
        System.out.println("size:" + set.size());


        // 注意：重写equals和hashCode一定要注意想equals的对象一定hashCode。hashCode相等的对象不一定相equals重写完后执行结果就是 1 啦
        Set<Person> set2 = new HashSet<>();
        set2.add(new Person(1, "zs", 1));
        set2.add(new Person(1, "zs", 1));
        System.out.println("size:" + set2.size());


        // 插入无序内部有序
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("b");
        treeSet.add("a");
        treeSet.add("d");
        treeSet.add("c");

        for (String str : treeSet) {
            System.out.println("treeSet:" + str);
        }


    }

}
