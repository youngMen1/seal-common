package com.seal.guava.joiner;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 10:47
 * @description List字符串集合转字符串，通过分隔符separator分隔
 **/
public class JoinerTest {


    public static void main(String[] args) {
        joiner();
    }

    /**
     * on():指定分隔符来分割字符串
     * limit():当分割的子字符串达到了limit个时则停止分割
     * fixedLength():根据长度来拆分字符串
     * trimResults():去掉子串中的空格
     * omitEmptyStrings():去掉空的子串
     * withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
     */
    public static void joiner() {
        // 检查数组越界
        Preconditions.checkElementIndex(1, 1);
        // 取决于传入的Boolean表达式
        Preconditions.checkArgument(1 > 2);
        // 检查参数是否为空
        Preconditions.checkNotNull(null);

        // [ a, b, c, d]
        System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));
        // [1 2,  3]
        System.out.println(Splitter.fixedLength(3).split("1 2 3"));
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
        // [1, 2, 3]
        System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));
        // [1, 2, 3],默认的连接符是,
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3"));
        // {a=1, b=2, c=3}
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));
    }

}
