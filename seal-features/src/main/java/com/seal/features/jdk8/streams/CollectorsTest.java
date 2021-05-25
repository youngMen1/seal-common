package com.seal.features.jdk8.streams;

import com.seal.features.entity.Person;
import junit.framework.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Java 8 Stream 的终极技巧——Collectors 操作 ！
 *
 * @author fengzhiqiang
 * @date 2021/5/25 13:34
 **/
public class CollectorsTest extends BaseTestCase {

    @Test
    public void test1() {
        List<Integer> result = IntStream.range(0, 1000).boxed().collect(Collectors.toCollection(ArrayList<Integer>::new));
        Assert.assertEquals(1000, result.size());
    }

    @Test
    public void test2() {
        List<Integer> result = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        Assert.assertEquals(1000, result.size());
    }

    @Test
    public void test3() {
        Map<Integer, Integer> map =
                IntStream.range(0, 1000).boxed()
                        .collect(
                                Collectors.toMap(
                                        Function.<Integer>identity(),
                                        (x) -> x % 3
                                )
                        );
        Assert.assertEquals(1000, map.size());
        Assert.assertEquals(Integer.valueOf(0), map.get(111));
    }

    /**
     * 将元素以某种规则连接起来。
     * 该方法有三种重载 joining(CharSequence delimiter)
     * 和 joining(CharSequence delimiter,CharSequence prefix,CharSequence suffix)
     * <p>
     * 用的比较多的是读取 HttpServletRequest 中的 body ：
     * HttpServletRequest.getReader().lines().collect(Collectors.joining());
     */
    @Test
    public void Test5() {
        // 输出 FelordcnTomcatJettyUndertowResin
        String result1 = servers.stream().collect(Collectors.joining());
        System.out.println(result1);

        // 输出 Felordcn,Tomcat,Jetty,Undertow,Resin
        String result2 = servers.stream().collect(Collectors.joining(","));
        System.out.println(result2);

        // 输出 [Felordcn,Tomcat,Jetty,Undertow,Resin]
        String result3 = servers.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(result3);
    }

    /**
     * 该方法先执行了一个归纳操作，然后再对归纳的结果进行 Function 函数处理输出一个新的结果。
     */
    @Test
    public void collectingAndThenTest() {
        // 比如我们将servers joining 然后转成大写，结果为： FELORDCN,TOMCAT,JETTY,UNDERTOW,RESIN
        String result = servers.stream().collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
        System.out.println(result);
    }

    /**
     * 按照条件对元素进行分组，和 SQL 中的 group by 用法有异曲同工之妙，
     * 通常也建议使用 Java 进行分组处理以减轻数据库压力。groupingBy
     * 也有三个重载方法 我们将 servers 按照长度进行分组:
     */
    @Test
    public void groupingByTest() {
        // 按照字符串长度进行分组符合条件的元素将组成一个 List 映射到以条件长度为key 的 Map<Integer, List<String>> 中
        Map<Integer, List<String>> result = servers.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(result);

        System.out.println("================================================================");

        /**
         * 如果我不想 Map 的 value 为 List 怎么办？ 上面的实现实际上调用了下面的方式：
         */

        // Map<Integer, Set<String>>
        Map<Integer, Set<String>> result2 = servers.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(result2);

        System.out.println("================================================================");

        // 我要考虑同步安全问题怎么办？ 当然使用线程安全的同步容器啊，那前两种都用不成了吧！ 别急！ 我们来推断一下，其实第二种等同于下面的写法:
        Supplier<Map<Integer, Set<String>>> mapSupplier1 = HashMap::new;
        Map<Integer, Set<String>> collect1 = servers.stream().collect(Collectors.groupingBy(String::length, mapSupplier1, Collectors.toSet()));
        System.out.println(collect1);

        System.out.println("================================================================");

        // 这就非常好办了，我们提供一个同步 Map 不就行了，于是问题解决了：
        // 其实同步安全问题 Collectors 的另一个方法 groupingByConcurrent 给我们提供了解决方案。用法和 groupingBy 差不多。
        Supplier<Map<Integer, Set<String>>> mapSupplier = () -> Collections.synchronizedMap(new HashMap<>(16));
        Map<Integer, Set<String>> collect = servers.stream().collect(Collectors.groupingBy(String::length, mapSupplier, Collectors.toSet()));
        System.out.println(collect);

        // 结果如下：

        // {5=[Jetty, Resin], 6=[Tomcat], 8=[Felordcn, Undertow]}
        //================================================================
        //{5=[Resin, Jetty], 6=[Tomcat], 8=[Felordcn, Undertow]}
        //================================================================
        //{5=[Resin, Jetty], 6=[Tomcat], 8=[Felordcn, Undertow]}
        //================================================================
        //{5=[Resin, Jetty], 6=[Tomcat], 8=[Felordcn, Undertow]}
    }


    /**
     * partitioningBy 我们在本文开头的提到的文章中已经见识过了，可以看作 groupingBy 的一个特例，基于断言（Predicate）策略分组。
     */
    @Test
    public void partitioningByTest() {
        // servers.stream().collect(Collectors.partitioningBy());
    }

    /**
     * 该方法归纳元素的的数量，非常简单
     */
    @Test
    public void countingTest() {
        // servers.stream().collect(Collectors.partitioningBy());
    }

    /**
     * Java 8允许在接口中加入具体方法。接口中的具体方法有两种，default方法和static方法，identity()就是Function接口的一个静态方法。
     * Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式
     */
    @Test
    public static void identityTest() {
        Map<String, Integer> map = servers.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);
    }

    /**
     * 这两个方法分别提供了查找大小元素的操作，它们基于比较器接口 Comparator 来比较 ，
     * 返回的是一个 Optional 对象。 我们来获取 servers 中最小长度的元素:
     * 这里其实 Resin 长度也是最小，这里遵循了 "先入为主" 的原则 。当然 Stream.min() 可以很方便的获取最小长度的元素。maxBy 同样的道理。
     */
    @Test
    public void maxByOrMinByTest() {
        // Jetty
        Optional<String> min = servers.stream().collect(Collectors.minBy(Comparator.comparingInt(String::length)));
    }

    /**
     * summingInt/Double/Long
     * 用来做累加计算。计算元素某个属性的总和,类似 Mysql 的 sum 函数，
     * 比如计算各个项目的盈利总和、计算本月的全部工资总和等等。我们这里就计算一下 servers 中字符串的长度之和 （为了举例不考虑其它写法）。
     */
    @Test
    public void summingIntTest() {
        // 总长度 32
        servers.stream().collect(Collectors.summingInt(s -> s.length()));
    }

    /**
     * summarizingInt/Double/Long
     * 如果我们对3.6章节-3.8章节的操作结果都要怎么办？难不成我们搞5个Stream流吗？ 所以就有了summarizingInt、summarizingDouble、summarizingLong三个方法。
     * 这三个方法通过对元素某个属性的提取，会返回对元素该属性的统计数据对象
     * 结果 DoubleSummaryStatistics 中包含了 总数，总和，最小值，最大值，平均值 五个指标。
     */
    public void summingIntTest2() {
        DoubleSummaryStatistics doubleSummaryStatistics = servers.stream().collect(Collectors.summarizingDouble(String::length));
        // {count=5, sum=32.000000, min=5.000000, average=6.400000, max=8.000000}
        System.out.println("doubleSummaryStatistics.toString() = " + doubleSummaryStatistics.toString());
    }

    /**
     * 该方法是先对元素使用 Function 进行再加工操作，然后用另一个Collector 归纳。
     * 比如我们先去掉 servers 中元素的首字母，然后将它们装入 List 。
     */
    public void mappingTest() {
        // [elordcn, omcat, etty, ndertow, esin]
        servers.stream().collect(Collectors.mapping(s -> s.substring(1), Collectors.toList()));

        // 有点类似 Stream 先进行了 map 操作再进行 collect ：

        servers.stream().map(s -> s.substring(1)).collect(Collectors.toList());
    }

    /**
     * 这个方法非常有用！但是如果要了解这个就必须了解其参数BinaryOperator<T>。 这是一个函数式接口，是给两个相同类型的量，
     * 返回一个跟这两个量相同类型的一个结果，伪表达式为(T,T) -> T。
     * 默认给了两个实现maxBy和minBy，根据比较器来比较大小并分别返回最大值或者最小值。当然你可以灵活定制。然后reducing就很好理解了，
     * 元素两两之间进行比较根据策略淘汰一个，
     * 随着轮次的进行元素个数就是reduce的。那这个有什么用处呢？ Java 官方给了一个例子：统计每个城市个子最高的人。
     */
    public void reducingTest() {
        // 见ReducingDemo
   }

}
