package com.seal.util.beanutil;

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

    public static <T, S> List<T> mapping(Class<T> clazz, List<S> list) {
        List<T> resultList = new ArrayList<>();
        for (S s : list) {
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(s, t);
                resultList.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }


    /**
     * 复制，并创建对象
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

        }
        return null;
    }


    /**
     * 对象转map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>(16);

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

}
