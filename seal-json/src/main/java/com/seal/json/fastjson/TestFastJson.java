package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.seal.json.dto.PersonDto;
import com.seal.json.entity.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:31
 * @description
 **/
public class TestFastJson {

    public static void main(String[] args) {
        method1();
        method2();
        method3();
    }


    static void method3() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("詹三", "1"));
        personList.add(new Person("詹2", "1"));
        personList.add(new Person("詹三", "2"));
        personList.add(new Person("詹4", "18"));
        personList.add(new Person("", ""));

        List<String> list = personList.stream().
                collect(Collectors.groupingBy(dog -> dog.getName() + dog.getAge(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(list.toString());
    }


    static void method1() {

        System.out.println("javabean转化示例开始----------");
        PersonDto personDto = new PersonDto("1", "fastjson", 1);

        // 这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(personDto);
        System.out.println(jsonString);
        // 这里将json字符串转化成javabean对象,
        personDto = JSON.parseObject(jsonString, PersonDto.class);
        System.out.println(personDto.toString());

        System.out.println("javabean转化示例结束----------");
    }

    static void method2() {
        System.out.println("List<javabean>转化示例开始----------");
        PersonDto personDto1 = new PersonDto("1", "fastjson1", 1);
        PersonDto personDto2 = new PersonDto("2", "fastjson2", 2);
        List<PersonDto> personDtos = new ArrayList<PersonDto>();
        personDtos.add(personDto1);
        personDtos.add(personDto2);
        String jsonString = JSON.toJSONString(personDtos);
        System.out.println("json字符串:" + jsonString);

        // 解析json字符串
        List<PersonDto> persons2 = JSON.parseArray(jsonString, PersonDto.class);
        // 输出解析后的person对象，也可以通过调试模式查看persons2的结构
        System.out.println("person1对象：" + persons2.get(0).toString());
        System.out.println("person2对象：" + persons2.get(1).toString());

        System.out.println("List<javabean>转化示例结束----------");
    }
}
