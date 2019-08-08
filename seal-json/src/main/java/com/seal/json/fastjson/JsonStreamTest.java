package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.seal.json.dto.SnowDto;

import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 11:44
 * @description Fastjson 对 OutputStream/InputStream 支持
 * Fastjson writeJSONString方法：
 * JSON类新增对OutputStream/Writer直接支持。
 **/
public class JsonStreamTest {

    public static void main(String[] args) {

    }

    /**
     * JSON类新增对OutputStream/Writer直接支持。
     */
    public static void getJsonString() {
        SnowDto snowDto = new SnowDto();
        // OutputStream os = ...;
//        JSON.writeJSONString(os, snowDto);
//        JSON.writeJSONString(os, Charset.from("GB18030"), snowDto);
//
//        Writer writer = ...;
//        JSON.writeJSONString(os, snowDto);
    }

    /**
     * fastjson新增加了对InputStream的支持支持。
     */
    public static void getJsonInputString() {
        // InputStream is = ...
//        SnowDto model = JSON.parseObject(is, SnowDto.class);
//        SnowDto model2 = JSON.parseObject(is, Charset.from("UTF-8"), SnowDto.class);
    }


}
