package com.seal.jackson.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seal.jackson.entity.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/23 10:33
 * @description
 **/
public class Test {

    public static void main(String[] args) {
        test();
    }

    /**
     * 除了使用Java类进行映射之外，我们还可以直接使用Map和List等Java集合组织JSON数据，
     * 在需要的时候可以使用readTree方法直接读取JSON中的某个属性值。需要注意的是从JSON转换为Map对象的时候，
     * 由于Java的类型擦除，所以类型需要我们手动用new TypeReference<T>给出。
     */
    private static void test() {
        try {

            Person person1 = new Person();
            person1.setName("张三");
            person1.setAge("李四");

            Person person2 = new Person();
            person2.setName("王五");
            person2.setAge("刘四");

            List<Person> list = new ArrayList<>();
            list.add(person1);
            list.add(person2);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>(16);
            map.put("name", "周于");
            map.put("age", "25");
            map.put("interests", list);

            // 对象转为字符串
            String text = mapper.writeValueAsString(map);
            System.out.println("text:" + text);
            Map<String, Object> map2 = null;
            map2 = mapper.readValue(text, new TypeReference<Map<String, Object>>() {
            });
            System.out.println("map2:" + map2);

            JsonNode root = mapper.readTree(text);
            String name = root.get("name").asText();
            int age = root.get("age").asInt();
            System.out.println("name:" + name + " age:" + age);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
