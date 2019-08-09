package com.seal.guava.joiner;

import com.google.common.base.*;
import com.google.common.collect.ImmutableMap;
import lombok.var;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 10:47
 * @description List字符串集合转字符串，通过分隔符separator分隔
 **/
public class JoinerTest {


    public static void main(String[] args) {
        //joiner();
        getJoiner();
        getMapJoinner();
        getSplitter();
        getMapSplitter();
        getCharMatcher();
        getString();

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

    private static String getJoiner() {
        // skipNulls()方法是直接忽略null
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
        // returns "1,5,7"
        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7)));


        /**
         * 想把这个集合转换成以#分割的字符串，并过滤掉集合中的空元素
         */
        List<Integer> eleList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, null);
        // 分隔符
        String joinStr = Joiner.on("#")
                // 过滤null元素
                .skipNulls()
                // 要分割的集合
                .join(eleList);
        System.out.println(joinStr);

        return joiner.join("Harry", null, "Ron", "Hermione");
    }

    /**
     * 主要对url的param的编码
     */
    private static void getMapJoinner() {
        Map<String, String> of = ImmutableMap.of("id", "123", "name", "green");
        String join = Joiner.on("&").withKeyValueSeparator("=").join(of);
        System.out.println("join:{}" + join);
    }

    private static String getSplitter() {
        // 返回Iterable<String>，其中包含”foo”、”bar”和”qux”。Splitter可以被设置为按照任何模式、字符、字符串或字符匹配器拆分。
        var split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,qux");
        System.out.println("split:" + split);

        /**
         * 分割
         * 有这样一个字符串"1,2,3,4,5,6,7"，要把这个字符串以,分割，并放到一个集合里面
         */
        String str = "1,2,3,4,5,6,7";
        List<String> stringList = Splitter.on(",")
                .trimResults()
                .splitToList(str);
        System.out.println(stringList);
        return split.toString();
    }

    private static void getMapSplitter() {
        String str = "id=123&name=green";
        Map<String, String> split = Splitter.on("&")
                .withKeyValueSeparator("=")
                .split(str);
        System.out.println("split:{}" + split);
    }


    private static void getCharMatcher() {
        String string = "asvsf**sf124536554";
        // 移除control字符
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string);
        System.out.println(noControl);
        // 只保留数字字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string);
        System.out.println(theDigits);
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        System.out.println(spaced);
        // 去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*");
        System.out.println(noDigits);
        // 用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
        System.out.println(lowerAndDigit);
        String a = CharMatcher.is('a').toString();
        System.out.println(a);
        // 只保留数字和小写字母
    }


    private static void getString() {
        //1、补右全（Strings.padEnd方法）
        String a = "12345";
        String b = Strings.padEnd(a, 10, 'x');
        System.out.println(b);
        //输出：12345xxxxx

        //2、补左全（Strings.padStart）
        String c = Strings.padStart(a, 10, 'x');
        System.out.println(c);
        //输出：xxxxx12345

        //3、校验空值和null
        String d = "";
        String f = null;
        boolean e = Strings.isNullOrEmpty(d);
        boolean h = Strings.isNullOrEmpty(f);
        System.out.println(e);
        System.out.println(h);
        //输出：true,true

        //4、如果为null 转为""
        String m = null;
        String n = Strings.nullToEmpty(m);
        System.out.println(n);
        //输出：

        //5、如果为"" 转为null
        String j = "";
        String k = Strings.emptyToNull(j);
        System.out.println(k);
        //输出：null

        //6、重复字符串（Strings.repeat）
        String o = "123";
        String p = Strings.repeat(o, 3);
        System.out.println(p);
        //输出：123123123

        //7、获取a,b左公共部分字符串（左边第一个公共部分）
        String r = "abcdsfsfs";
        String s = "accdc3sfsd";
        String t = Strings.commonPrefix(r, s);
        System.out.println(t);
        //输出：a

        //8、获取a,b右公共部分字符串
        String w = "faaxyz";
        String x = "fwefxyz";
        String z = Strings.commonSuffix(w, x);
        System.out.println(z);
        //输出：xyz
    }

}
