package com.seal.jackson.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.seal.jackson.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 11:56
 * @description json 工具类
 **/
@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        // 转换为格式化的json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

//        // 美化输出
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        // 允许序列化空的POJO类
//        // （否则会抛出异常）
//        this.objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        // 把java.util.Date, Calendar输出为数字（时间戳）
//        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        // 在遇到未知属性的时候不抛出异常
//        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        // 强制JSON 空字符串("")转换为null对象值:
//        this.objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//
//        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
//        this.objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        // 允许没有引号的字段名（非标准）
//        this.objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        // 允许单引号（非标准）
//        this.objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        // 强制转义非ASCII字符
//        this.objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
//        this.objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
    }


    public String toJson(Object object) {
        String jsonContent = null;
        try {
            // 对象转为字符串,Map转为字符串
            jsonContent = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return jsonContent;
    }

    public byte[] toByte(Object object) {
        byte[] byteArr = null;
        try {
            // 对象转为byte数组
            byteArr = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return byteArr;
    }

    public Object toObject(String jsonStr) {
        Object Object = null;
        try {
            // json字符串转为对象
            Object = objectMapper.readValue(jsonStr, Object.class);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return Object;
    }

    public Object toObject(byte[] byteArr) {
        Object Object = null;
        try {
            // byte数组转为对象
            Object = objectMapper.readValue(byteArr, Object.class);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return Object;
    }

    public static List<String> toList(String json) {
        List<String> list = Collections.emptyList();
        try {
            if (StringUtils.hasText(json)) {
                list = objectMapper.readValue(json, List.class);
            }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
        }
        return list;
    }

    public static <T> T fromJson(String json, TypeReference<T> jsonTypeReference) {
        try {
            if (json == null) {
                return null;
            }
            return objectMapper.readValue(json, jsonTypeReference);
        } catch (Exception e) {
            log.error("From json failure, json:{}", json);
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        String json = "[{\"name\":\"张三\",\"age\":\"李四\",\"birthday\":null},{\"name\":\"王五\",\"age\":\"刘四\",\"birthday\":null}]";

        System.out.println(toList(json));
        System.out.println(fromJson(json, new TypeReference<List<Person>>(){}));
    }
}
