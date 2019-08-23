package com.seal.commons.collections;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/22 17:48
 * @description 新接口被添加到支持双向映射。 使用双向映射，可以使用值查找键，并且可以使用键轻松查找值
 **/
public class BidiMapTest {
    public static void main(String[] args) {

        BidiMap<String, String> map = new DualHashBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        // 多出来的一种遍历方式  还是分厂人性化的
        MapIterator<String, String> it = map.mapIterator();
        while (it.hasNext()) {
            it.next(); //此句话必须调用  返回的是key，效果同getKey，但必须调用
            System.out.println(it.getKey() + "---" + it.getValue());
        }
        // value1
        System.out.println(map.get("key1"));
        // 根据value拿key // key1
        System.out.println(map.getKey("value1"));
        // 这个方法是Map接口的 // defaultValue
        System.out.println(map.getOrDefault("k", "defaultValue"));
        // 返回一个逆序的视图  注意是视图
        BidiMap<String, String> inverseMap = map.inverseBidiMap();

        // 根据key删除
        inverseMap.remove("key1");
        // 根据value删除
        inverseMap.removeValue("value2");
        // {key1=value1, key2=value2, key3=value3}
        System.out.println(map);
        // {value2=key2, value1=key1, value3=key3}
        System.out.println(inverseMap);

    }
}
