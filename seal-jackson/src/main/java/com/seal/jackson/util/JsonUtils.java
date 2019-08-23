package com.seal.jackson.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/1 11:56
 * @description json 工具类
 **/
public class JsonUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        // 转换为格式化的json
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    public String toJson(Object object) {
        String jsonContent = null;
        try {
            // 对象转为字符串,Map转为字符串
            jsonContent = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
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
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
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
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
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
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
        }
        return Object;
    }

    public List<String> toList(String json) {
        List<String> list = Collections.emptyList();
        try {
            if (StringUtils.hasText(json)) {
                list = objectMapper.readValue(json, List.class);
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
        }
        return list;
    }
}
