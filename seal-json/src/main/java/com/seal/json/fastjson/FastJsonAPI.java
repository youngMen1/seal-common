package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.seal.json.dto.SnowDto;
import lombok.var;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 16:15
 * @description JSON这个类是fastjson API的入口，主要的功能都通过这个类提供。
 * 序列化API
 * JSON字符串反序列化API
 * API:JSON
 **/
public class FastJsonAPI {


    public static void main(String[] args) {
        // parse Tree
        parseTree();
        // parse POJO
        parsePOJO();
        // parse POJO Generic
        parsePOJOGeneric();
        // convert POJO to json bytes
        jsonBytes();
        // map转java实体
        mapToEntity();
    }

    /**
     * parse Tree
     */
    private static JSONObject parseTree() {
        SnowDto snowDto = new SnowDto();
        snowDto.setName("seal");
        snowDto.setAge("23");
        snowDto.setSex("男");
        snowDto.setAddress("广东省深圳市");
        // 将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
        var jsonStr = JSON.toJSONString(snowDto);

        // 将JSON字符串反序列化为JavaBean
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        System.out.println("json:" + jsonObj);
        return jsonObj;
    }

    /**
     * parse POJO
     *
     * @return
     */
    private static SnowDto parsePOJO() {
        String jsonStr = "{\"address\":\"广东省深圳市\",\"sex\":\"男\",\"name\":\"seal\",\"age\":\"23\"}";
        SnowDto snowDto = JSON.parseObject(jsonStr, SnowDto.class);
        System.out.println("json:" + snowDto);
        return snowDto;
    }

    /**
     * parse POJO Generic
     *
     * @return
     */
    private static List<SnowDto> parsePOJOGeneric() {
        String jsonStr = "[{\"address\":\"广东省深圳市\",\"sex\":\"男\",\"name\":\"seal\",\"age\":\"23\"},{\"address\":\"广东省深圳市\",\"sex\":\"男\",\"name\":\"seal\",\"age\":\"23\"}]";
        Type type = new TypeReference<List<SnowDto>>() {
        }.getType();
        List<SnowDto> list = JSON.parseObject(jsonStr, type);
        System.out.println("listJson:" + list);
        return list;
    }

    /**
     * convert POJO to json bytes
     *
     * @return
     */
    private static byte[] jsonBytes() {
        SnowDto snowDto = new SnowDto();
        snowDto.setName("seal");
        snowDto.setAge("23");
        snowDto.setSex("男");
        snowDto.setAddress("广东省深圳市");
        byte[] jsonBytes = JSON.toJSONBytes(snowDto);
        System.out.println("jsonBytes:" + jsonBytes);


        // write POJO as json string to OutputStream
        // SnowDto snowDto = new SnowDto();
        // 输出流
        // OutputStream os;
        // JSON.writeJSONString(os, snowDto);

        // write POJO as json string to Writer
        // SnowDto snowDto = new SnowDto();
        // Writer writer = ...;
        // JSON.writeJSONString(writer, snowDto);

        return jsonBytes;
    }


    private static void mapToEntity() {
        Map hash = new HashMap(16);
        hash.put("name", "张三");
        hash.put("age", "23");
        hash.put("sex", "男");

        String jsonString = JSON.toJSONString(hash);

        // SnowDto student = (SnowDto)JSONObject.toJavaObject((JSON)JSONObject.toJSON(map), SnowDto.class);
        SnowDto snowDto = JSON.parseObject(jsonString, SnowDto.class);
        System.out.println(snowDto);
    }


}
