package com.seal.util.beanutil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seal.util.entity.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/2/18 14:40
 * @description Json工具类
 **/
@Slf4j
public class JacksonBeanUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转json
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> String toJson(T data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (Exception e) {
            log.error("To json failure, data:{}", data);
            throw new RuntimeException(e);
        }
    }

    /**
     * json转java类
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJsonObject(String json, Class<T> clazz) {
        try {
            if (json == null) {
                return null;
            }
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error("From json failure, json:{}", json);
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转成集合对象
     *
     * @param json
     * @param jsonTypeReference
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> jsonTypeReference) {
        try {
            if (json == null) {
                return null;
            }
            return MAPPER.readValue(json, jsonTypeReference);
        } catch (Exception e) {
            log.error("From json failure, json:{}", json);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        String json = "[{\"name\":\"张三\",\"age\":\"李四\",\"birthday\":null},{\"name\":\"王五\",\"age\":\"刘四\",\"birthday\":null}]";
        System.out.println(fromJson(json, new TypeReference<List<Person>>() {
        }));

        String jsonObject = "{\"name\":\"张三\",\"age\":\"李四\",\"birthday\":null}";
        System.out.println(fromJsonObject(jsonObject, Person.class));

        Person person = new Person();
        person.setName("张三");
        person.setAge("12");

        System.out.println(toJson(person));

    }

}
