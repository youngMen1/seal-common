package com.seal.json.fastjson.util;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:51
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        String string = "{\"address\":\"广东省深圳市\",\"sex\":\"男\",\"name\":\"seal\",\"age\":\"23\"}";
        System.out.println(FastJsonUtils.fromString(string, "address"));


    }
}
