package com.seal.util.beanutil;

import java.util.Date;

import com.seal.util.entity.Person;
import com.seal.util.entity.PersonDto;
import lombok.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 15:46
 * @description
 **/
@Slf4j
public class BeanUtil {

    /**
     * 集合复制
     * 字段要一样
     *
     * @param clazz
     * @param list
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> mapping(Class<T> clazz, List<S> list) {
        List<T> resultList = new ArrayList<>();
        for (S s : list) {
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(s, t);
                resultList.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("To json mapping, data:{}");
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }


    /**
     * 复制，并创建对象
     * 字段要一样
     *
     * @param o
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object o, Class<T> cla) {
        try {
            T t = cla.newInstance();
            BeanUtils.copyProperties(o, t);
            return t;
        } catch (Exception e) {
            log.error("To json copyObject, data:{}");
            throw new RuntimeException(e);
        }
    }


    /**
     * 对象转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>(16);

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                log.error("To json objectToMap, data:{}");
                throw new RuntimeException(e);
            }
        }
        return map;
    }


    public static void main(String[] args) {

        Person person = new Person();
        person.setName("王麻子");
        person.setAge("26");
        person.setBirthday(new Date());


        Person person2 = new Person();
        person2.setName("王麻子");
        person2.setAge("26");
        person2.setBirthday(new Date());

        List list = new ArrayList();
        list.add(person);
        list.add(person2);

        /**
         * 集合复制
         */
        var mapping = mapping(PersonDto.class, list);
        System.out.println("mapping:{}" + mapping);

        /**
         * 对象复制
         */
        var copyObject = copyObject(person, PersonDto.class);
        System.out.println("copyObject:{}" + copyObject);

        /**
         * 对象转map
         */
        var objectMap = objectToMap(person);
        System.out.println(objectMap.get("name"));
        System.out.println("objectMap:{}" + objectMap);

    }


}
