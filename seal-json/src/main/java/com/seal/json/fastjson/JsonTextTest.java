package com.seal.json.fastjson;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.seal.json.dto.SnowDto;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
            // 先调用startArray
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

    /**
     * 超大JSON对象序列化
     * 如果你的JSON格式是一个巨大的JSONObject，有很多Key/Value对，
     * 则先调用startObject，然后挨个写入Key和Value，然后调用endObject。
     *
     * @return
     */
    private static JSONWriter getJSONWriter2() {
        JSONWriter writer = null;
        try {
            writer = new JSONWriter(new FileWriter("/tmp/huge.json"));
            // 先调用startObject
            writer.startObject();
            for (int i = 0; i < 1000 * 1000; ++i) {
                writer.writeKey("x" + i);
                writer.writeValue(new SnowDto());
            }
            writer.endObject();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    /**
     * 反序列化JSON数组序列化
     *
     * @return
     */
    private static JSONReader getJSONReader() {
        JSONReader reader = null;
        try {
            reader = new JSONReader(new FileReader("/tmp/huge.json"));
            // 先调用startArray
            reader.startArray();
            while (reader.hasNext()) {
                SnowDto snowDto = reader.readObject(SnowDto.class);
                // handle vo ...
            }
            reader.endArray();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }

    /**
     * 反序列化JSON对象序列化
     *
     * @return
     */
    private static JSONReader getJSONReader2() {
        JSONReader reader = null;
        try {
            reader = new JSONReader(new FileReader("/tmp/huge.json"));
            // 先调用startObject
            reader.startObject();
            while (reader.hasNext()) {
                String key = reader.readString();
                SnowDto snowDto = reader.readObject(SnowDto.class);
                // handle vo ...
            }
            reader.endObject();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }


}
