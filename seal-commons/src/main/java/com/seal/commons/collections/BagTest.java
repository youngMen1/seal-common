package com.seal.commons.collections;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.Iterator;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 17:25
 * @description 新的接口被添加到支持bag。 Bag接口定义了一个集合，它可以计算一个对象出现在集合中的次数。
 * 例如，如果Bag包含{a，a，b，c}，则getCount("a")方法将返回2，而uniqueSet()返回唯一值。
 **/
public class BagTest {
    public static void main(String[] args) {
        Bag hashBag = new HashBag();
        String s1 = "s1";
        String s2 = "s2";
        hashBag.add(s1);
        hashBag.add(s1);
        //一次性放置多个元素
        hashBag.add(s2, 3);

        // 获得包中元素迭代器
        Iterator<?> iterator = hashBag.iterator();
        System.out.println("包中元素为：");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 5
        System.out.println("包中元素个数为：" + hashBag.size());
        //下面两个特有的方法 使用起来较为方便 2
        System.out.println("包中entity1个数为：" + hashBag.getCount(s1));
        // 2
        System.out.println("去重后个数为：" + hashBag.uniqueSet().size());

    }

}
