package com.seal.json.fastjson;

import com.alibaba.fastjson.JSONWriter;
import com.seal.json.dto.SnowDto;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 18:20
 * @description Fastjson API Stream
 * Fastjson当需要处理超大JSON文本时，需要Stream API，在fastjson-1.1.32版本中开始提供Stream API。
 **/
public class JsonTextTest {

    public static void main(String[] args) {
        getJSONWriter();
    }

    /**
     * 超大JSON数组序列化
     * 如果你的JSON格式是一个巨大的JSON数组，有很多元素，
     * 则先调用startArray，然后挨个写入对象，然后调用endArray。
     *
     * @return
     */
    private static JSONWriter getJSONWriter() {
        JSONWriter writer = null;
        try {
            writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
            writer.startArray();
            for (int i = 0; i < 1000 * 1000; ++i) {
                writer.writeValue(new SnowDto());
            }
            writer.endArray();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }



}
