package com.seal.json.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
        String str = "[{\"Batch\":\"CS_5592123532097705862\",\"Number\":\"5094229290153102780\",\"Password\":\"885267\",\"ValidityTime\":\"2019-12-31 23:59:59\",\"OrderNo\":\"52636089499936097457\",\"Status\":2,\"StatusName\":\"已领取\"}]";

        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject);
    }
}
