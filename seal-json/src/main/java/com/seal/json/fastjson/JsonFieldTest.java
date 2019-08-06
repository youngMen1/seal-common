package com.seal.json.fastjson;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.seal.json.dto.SnowLittleDto;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 16:10
 * @description Fastjson 定制序列化
 * 1. 简介
 * fastjson支持多种方式定制序列化。
 * 通过@JSONField定制序列化
 * 通过@JSONType定制序列化
 * 通过SerializeFilter定制序列化
 * 通过ParseProcess定制反序列化
 **/
public class JsonFieldTest {

    public static void main(String[] args) {
        parseJsonField();
    }

    private static String parseJsonField() {
        SnowLittleDto snowLittleDto = new SnowLittleDto();
        snowLittleDto.setId("1000");
        snowLittleDto.setName("seal");
        snowLittleDto.setAge("23");
        snowLittleDto.setSex("男");
        snowLittleDto.setAddress("广东省深圳市");
        snowLittleDto.setCreateTime(new Date());

        String jsonString = JSON.toJSONString(snowLittleDto);
        System.out.println("jsonString:" + jsonString);
        return jsonString;
    }


}
