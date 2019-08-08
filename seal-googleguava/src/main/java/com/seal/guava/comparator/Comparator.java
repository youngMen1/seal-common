package com.seal.guava.comparator;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.seal.guava.dto.FooDto;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 17:26
 * @description 排序: Guava强大的”流畅风格比较器”
 * Guava流畅风格比较器[Comparator]的实现，它可以用来为构建复杂的比较器，以完成集合排序的功能。
 * 链式调用方法，来定制和增强现有的比较器。
 **/
public class Comparator {
    public static void main(String[] args) {
        createOrdering();
        //getOrdering();
    }


    public static void createOrdering() {
        /**
         * 实现自定义的排序器时，除了用上面的from方法，也可以跳过实现Comparator，而直接继承Ordering
         */
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };


        /**
         * 考虑到排序器应该能处理sortedBy为null的情况，我们可以使用下面的链式调用来合成排序器：
         * 当阅读链式调用产生的排序器时，应该从后往前读。
         * 下面的例子中，排序器首先调用apply方法获取sortedBy值，并把sortedBy为null的元素都放到最前面，
         * 然后把剩下的元素按sortedBy进行自然排序。
         * 之所以要从后往前读，是因为每次链式调用都是用后面的方法包装了前面的排序器。
         */
        Ordering<FooDto> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<FooDto, String>() {
            @Override
            public String apply(FooDto foo) {
                return foo.sortedBy;
            }
        });
        ArrayList<FooDto> names = Lists.newArrayList();
        FooDto fooDto = new FooDto();
        fooDto.setSortedBy("Naresh");
        fooDto.setNotSortedBy(1);
        names.add(fooDto);

        FooDto fooDto2 = new FooDto();
        fooDto2.setSortedBy("Mohan");
        fooDto2.setNotSortedBy(2);
        names.add(fooDto2);
        Collections.sort(names, ordering);
        System.out.println(names);
        System.out.println("最大值：" + ordering.max(names));
        System.out.println("最小值：" + ordering.min(names));


    }


    private static void getOrdering() {
        // 构造一个比较器，先根据字符串长度排序，再按照字典序排序，null值置前
        Ordering<String> ordering = Ordering.natural()
                .onResultOf((String str) -> {
                    //使用Optional处理null值
                    return Optional.fromNullable(str).or("").length();
                })
                // compound用于合并两个Ordering
                .compound(Ordering.natural().nullsFirst());
        ArrayList<String> names = Lists.newArrayList("Ram", "Shyam", "Mohan", "Sohan",
                "Ramesh", "Suresh", "Naresh", "Mahesh", null, "", "Vikas", "Deepak");
        Collections.sort(names, ordering);
        System.out.println(names);
        System.out.println("最大的4个元素，从大到小：" + ordering.greatestOf(names, 4));
        System.out.println("最小的4个元素，从小到大：" + ordering.leastOf(names, 4));
        System.out.println("最大值：" + ordering.max(names));
        System.out.println("最小值：" + ordering.min(names));
    }


}
