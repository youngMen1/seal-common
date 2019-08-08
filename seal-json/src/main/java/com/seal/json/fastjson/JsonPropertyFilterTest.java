package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.seal.json.fastjson.serializefilter.PropertyFilter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 12:03
 * @description
 **/
public class JsonPropertyFilterTest {

    public static void main(String[] args) {

    }


    /**
     * 可以通过扩展实现根据object或者属性名称或者属性值进行判断是否需要序列化。
     * @param obj
     */
    private static void getPropertyFilter(Object obj) {
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                if ("id".equals(name)) {
                    int id = ((Integer) value).intValue();
                    return id >= 100;
                }
                return false;
            }
        };
        // 序列化的时候传入filter
        JSON.toJSONString(obj, filter);
    }
}
