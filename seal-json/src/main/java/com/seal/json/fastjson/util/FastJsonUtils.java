package com.seal.json.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.seal.json.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:45
 * @description fastJson 工具类
 **/
public class FastJsonUtils {

    /**
     * SerializeConfig：
     * 是对序列化过程中一些序列化过程的特殊配置，这里用作日期格式的定义。
     * 有关需要带类型的全类型序列化过程，需要调用JSON.toJSONStringZ()方法。
     * 需要美化输出时候，需要打开序列化美化开关，在方法中true那个参数。
     */
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }


    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * 转换成字符串 ,带有过滤器
     *
     * @param object
     * @return
     */
    public static String convertObjectToJSON(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 转成bean对象
     *
     * @param text
     * @return
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    /**
     * 转成具体的泛型bean对象
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 转换为数组Array
     *
     * @param text
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    /**
     * 转换为具体的泛型数组Array
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为具体的泛型List
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }


    /**
     * 将string转化为序列化的json字符串
     *
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        return JSON.parse(text);
    }

    /**
     * json字符串转化为map
     *
     * @param string
     * @return
     */
    public static <K, V> Map<K, V> stringToCollect(String string) {
        Map<K, V> map = (Map<K, V>) JSONObject.parseObject(string);
        return map;
    }

    /**
     * 转换JSON字符串为对象
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static Object convertJsonToObject(String jsonData, Class<?> clazz) {
        return JSONObject.parseObject(jsonData, clazz);
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static <K, V> String collectToString(Map<K, V> m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /***
     * 解析为字符串
     *
     * @param jsonString json字符串
     * @param key 关键字
     * @return 返回值
     */
    public static String fromString(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getString(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 解析为布尔
     *
     * @param jsonString json字符串
     * @param key 关键字
     * @return 返回值
     */
    public static Boolean formBoolean(String jsonString, String key) {
        try {
            if (jsonString != null && jsonString.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                return jsonObject.getBoolean(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
        String json = "[{\"name\":\"张三\",\"age\":\"李四\",\"birthday\":null},{\"name\":\"王五\",\"age\":\"刘四\",\"birthday\":null}]";
        // 集合类型，使用parseArray进行解析
        List<Person> parseArray = JSON.parseArray(json, Person.class);
        System.out.println(parseArray);

    }
}
