package com.seal.util.beanutil;

import com.google.common.collect.Lists;
import com.seal.util.entity.Person;
import com.seal.util.entity.PersonDto;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 15:46
 * @description
 **/
@Slf4j
public class BeanUtil {


    /**
     * List集合之间的对象属性赋值
     *
     * @param clazz 输出集合类型
     * @param input 输入集合
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> mapping(List<S> input, Class<T> clazz) {
        List<T> resultList = new ArrayList<>();
        for (S s : input) {
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
     * List集合之间的对象属性赋值
     *
     * @param input 输入集合
     * @param clazz 输出集合类型
     * @param <E>   输入集合类型
     * @param <T>   输出集合类型
     * @return 返回集合
     */
    public static <E, T> List<T> convertList(List<E> input, Class<T> clazz) {
        List<T> output = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(input)) {
            for (E source : input) {
                T target = BeanUtils.instantiateClass(clazz);
                BeanUtils.copyProperties(source, target);
                output.add(target);
            }
        }
        return output;
    }


    /**
     * 复制，并创建对象
     * 字段要一样
     *
     * @param source 源对象
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object source, Class<T> cla) {
        try {
            T t = cla.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            log.error("To json copyObject, data:{}");
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象属性拷贝 <br>
     * 将源对象的属性拷贝到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            log.error("BeanUtil property copy failed:Exception", e);
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
        var mapping = mapping(list, PersonDto.class);
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
