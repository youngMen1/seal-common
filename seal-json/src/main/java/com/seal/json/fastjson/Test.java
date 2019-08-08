package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.seal.json.dto.Bar;
import com.seal.json.dto.Foo;

import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:26
 * @description SerializeConfig：是对序列化过程中一些序列化过程的特殊配置，这里用作日期格式的定义。
 * 有关需要带类型的全类型序列化过程，需要调用JSON.toJSONStringZ()方法。
 * 需要美化输出时候，需要打开序列化美化开关，在方法中true那个参数。
 **/
public class Test {
    private static SerializeConfig mapping = new SerializeConfig();

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Date date = new Date();
        String text = JSON.toJSONString(date, mapping);
        System.out.println(text);
        System.out.println(JSON.toJSONString(foo, true));
        String x2 = JSON.toJSONString(foo, mapping);
        System.out.println(x2);

        json2List();
        json2Map();
        array2JSON();
        array2JSON2();

    }

    public static void json2List() {
        // List -> JSON array
        List<Bar> barList = new ArrayList<Bar>();
        barList.add(new Bar());
        barList.add(new Bar());
        barList.add(new Bar());
        String json = JSON.toJSONString(barList, true);
        System.out.println(json);
        // JSON array -> List
        List<Bar> barList1 = JSON.parseArray(json, Bar.class);
        for (Bar bar : barList1) {
            System.out.println(bar.toString());
        }
    }

    public static void json2Map() {
        // Map -> JSON
        Map<String, Bar> map = new HashMap<String, Bar>(16);
        map.put("a", new Bar());
        map.put("b", new Bar());
        map.put("c", new Bar());
        String json = JSON.toJSONString(map, true);
        System.out.println(json);
        // JSON -> Map
        Map<String, Bar> map1 = (Map<String, Bar>) JSON.parse(json);
        for (String key : map1.keySet()) {
            System.out.println(key + ":" + map1.get(key));
        }
    }

    public static void array2JSON() {
        String[] arr_String = {"a", "b", "c"};
        String json_arr_String = JSON.toJSONString(arr_String, true);
        System.out.println(json_arr_String);
        JSONArray jsonArray = JSON.parseArray(json_arr_String);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);
    }

    public static void array2JSON2() {
        Bar[] arr_Bar = {new Bar(), new Bar(), new Bar()};
        String json_arr_Bar = JSON.toJSONString(arr_Bar, true);
        System.out.println(json_arr_Bar);
        JSONArray jsonArray = JSON.parseArray(json_arr_Bar);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);
    }

    public static void map2JSON() {
        Map map = new HashMap(16);
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");
        String json = JSON.toJSONString(map);
        System.out.println(json);
        Map map1 = JSON.parseObject(json);
        for (Object o : map.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) o;
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }
}
