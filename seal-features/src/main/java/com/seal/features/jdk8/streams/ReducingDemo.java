package com.seal.features.jdk8.streams;


import com.seal.features.entity.City;
import com.seal.features.entity.Person;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

/**
 * 函数式编程风格：计算 1000000 个人所在不同城市最大身高的结果如下：（耗时 149 ms）
 * 成都 -> 185
 * 纽约 -> 200
 * <p>
 * 普通编程风格：计算 1000000 个人所在不同城市最大身高的结果如下：（耗时 82 ms）
 * 成都 -> 185
 * 纽约 -> 200
 *
 * @author fengzhiqiang
 * @date 2021/5/25 14:17
 **/
public class ReducingDemo {

    /**
     * 运行入口
     *
     * @param args 运行参数
     */
    public static void main(String[] args) {
        List<Person> personList = getPersonList(1000000);
        functionStyle(personList);
        normalStyle(personList);
    }

    /**
     * 函数式编程风格（3行处理代码）
     *
     * @param personList Person 列表
     */
    private static void functionStyle(List<Person> personList) {
        long start = System.currentTimeMillis();
        // 创建一个比较器，取名为 byHeight （通过高度来比较）
        Comparator<Person> byHeight = Comparator.comparingInt(Person::getHeight);
        // 创建一个归一收集器
        Map<City, Optional<Person>> tallestByCity = personList.stream().
                collect(groupingBy(Person::getCity, reducing(BinaryOperator.maxBy(byHeight))));
        long usedTime = System.currentTimeMillis() - start;
        printResult("函数式编程风格", personList.size(), usedTime, tallestByCity);
    }

    /**
     * 普通编程风格（20行处理代码）
     *
     * @param personList Person 列表
     */
    private static void normalStyle(List<Person> personList) {
        long start = System.currentTimeMillis();
        // 创建一个结果集
        Map<City, Optional<Person>> tallestByCity = new HashMap<>();
        // 第一步：找出所有的不同城市
        Set<City> cityList = new HashSet<>();
        for (Person person : personList) {
            if (!cityList.contains(person.getCity())) {
                cityList.add(person.getCity());
            }
        }
        // 第二部，遍历所有城市，遍历所有人找出每个城市的最大身高
        for (City city : cityList) {
            int maxHeight = 0;
            Person tempPerson = null;
            for (Person person : personList) {
                if (person.getCity().equals(city)) {
                    if (person.getHeight() > maxHeight) {
                        maxHeight = person.getHeight();
                        tempPerson = person;
                    }
                }
            }
            tallestByCity.put(city, Optional.ofNullable(tempPerson));
        }
        long usedTime = System.currentTimeMillis() - start;
        printResult("普通编程风格", personList.size(), usedTime, tallestByCity);
    }

    /**
     * 获取Person列表
     *
     * @param numbers 要获取的数量
     * @return 返回指定数量的 Person 列表
     */
    private static List<Person> getPersonList(int numbers) {
        // 创建城市
        final City cityChengDu = new City("成都");
        final City cityNewYork = new City("纽约");
        List<Person> people = new ArrayList<>();
        // 创建指定数量的Person，并指定不同的城市和相对固定的身高值
        for (int i = 0; i < numbers; i++) {
            if (i % 2 == 0) {
                // 成都最大身高185
                people.add(new Person(cityChengDu, 185));
            } else if (i % 3 == 0) {
                people.add(new Person(cityChengDu, 170));
            } else if (i % 5 == 0) {
                // 成都最小身高160
                people.add(new Person(cityChengDu, 160));
            } else if (i % 7 == 0) {
                // 纽约最大身高200
                people.add(new Person(cityNewYork, 200));
            } else if (i % 9 == 0) {
                people.add(new Person(cityNewYork, 185));
            } else if (i % 11 == 0) {
                // 纽约最小身高165
                people.add(new Person(cityNewYork, 165));
            } else {
                // 默认添加纽约最小身高165
                people.add(new Person(cityNewYork, 165));
            }
        }
        return people;
    }

    /**
     * 输出结果
     *
     * @param styleName     风格名称
     * @param totalPerson   总人数
     * @param usedTime      计算耗时
     * @param tallestByCity 统计好最大身高的城市分组MAP
     */
    private static void printResult(String styleName, long totalPerson, long usedTime, Map<City, Optional<Person>> tallestByCity) {
        System.out.println("\n" + styleName + "：计算 " + totalPerson + " 个人所在不同城市最大身高的结果如下：（耗时 " + usedTime + " ms）");
        tallestByCity.forEach((city, person) -> {
            person.ifPresent(p -> System.out.println(city.getName() + " -> " + p.getHeight()));
        });
    }

}

