package com.seal.json.fastjson;

import com.alibaba.fastjson.JSONPath;
import com.seal.json.dto.SnowBigDto;
import com.seal.json.dto.SnowDto;

import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 17:37
 * @description fastjson 1.2.0之后的版本支持JSONPath。这是一个很强大的功能，可以在java框架中当作对象查询语言（OQL）来使用。
 * API:JSONPath
 **/
public class JsonPathTest {
    public static void main(String[] args) {
        // 读取集合多个元素的某个属性
        getString();
        // 返回集合中多个元素
        getSize();
        // 按范围返回集合的子集
        getChild();
        // 通过条件过滤，返回集合的子集
        getFilterChild();
        // 根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
        // getIntValue();
        getSingletonMap();
    }

    /**
     * 读取集合多个元素的某个属性
     *
     * @return
     */
    private static List<String> getString() {
        List<SnowDto> snowDtoList = new ArrayList<>();
        snowDtoList.add(new SnowDto("wenshao", "23", "男", "广东省深圳市"));
        snowDtoList.add(new SnowDto("seal", "24", "男", "广东省珠海市"));
        // 返回snowDtoList的所有名称
        List<String> names = (List<String>) JSONPath.eval(snowDtoList, "$.name");
        System.out.println("返回snowDtoList的为name的名称:" + names);
        return names;
    }


    /**
     * 返回集合中多个元素
     *
     * @return
     */
    private static List<SnowDto> getSize() {
        List<SnowDto> snowDtoList = new ArrayList<>();
        snowDtoList.add(new SnowDto("wenshao", "23", "男", "广东省深圳市"));
        snowDtoList.add(new SnowDto("seal", "24", "男", "广东省珠海市"));
        // 返回下标为1和2的元素
        List<SnowDto> result = (List<SnowDto>) JSONPath.eval(snowDtoList, "[1,2]");
        System.out.println("返回下标为1和2的元素:" + result);
        return result;
    }

    /**
     * 按范围返回集合的子集
     *
     * @return
     */
    private static List<SnowDto> getChild() {
        List<SnowDto> snowDtoList = new ArrayList<>();
        snowDtoList.add(new SnowDto("wenshao", "23", "男", "广东省深圳市"));
        snowDtoList.add(new SnowDto("seal", "24", "男", "广东省珠海市"));
        // 返回下标从0到2的元素
        List<SnowDto> result = (List<SnowDto>) JSONPath.eval(snowDtoList, "[0:2]");
        System.out.println("返回下标从0到2的元素:" + result);
        return result;
    }


    /**
     * 通过条件过滤，返回集合的子集
     *
     * @return
     */
    private static List<Object> getFilterChild() {
        List<SnowDto> snowDtoList = new ArrayList<>();
        snowDtoList.add(new SnowDto("wenshao", "23", "男", "广东省深圳市"));
        snowDtoList.add(new SnowDto("seal", "24", "男", "广东省珠海市"));
        // 通过条件过滤，返回集合的子集
        List<Object> result = (List<Object>) JSONPath.eval(snowDtoList, "[name in (\"seal\")]");
        System.out.println("通过条件过滤，返回集合的子集:" + result);
        return result;
    }


    /**
     * 根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
     *
     * @return
     */
    private static SnowBigDto getIntValue() {
        SnowBigDto snowDto = new SnowBigDto();
        snowDto.setId(1001);
        snowDto.setName("seal");
        snowDto.setAge("23");
        snowDto.setSex("男");
        snowDto.setAddress("广东省深圳市");
        // 将name字段修改为jacky
        JSONPath.set(snowDto, "id", 123456);
        // 将value字段赋值为长度为0的数组
        JSONPath.set(snowDto, "value", new int[0]);
        // 将value字段的数组添加元素1,2,3
        JSONPath.arrayAdd(snowDto, "value", 1, 2, 3);
        System.out.println("snowDto:" + snowDto);
        return snowDto;
    }

    private static List<Object> getSingletonMap() {
        Map root = Collections.singletonMap("company",
                //
                Collections.singletonMap("departs",
                        //
                        Arrays.asList(
                                //
                                Collections.singletonMap("id",
                                        1001),
                                //
                                Collections.singletonMap("id",
                                        1002),
                                //
                                Collections.singletonMap("id", 1003)
                        )
                ));
        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        System.out.println("ids:" + ids);
        return ids;
    }
}
