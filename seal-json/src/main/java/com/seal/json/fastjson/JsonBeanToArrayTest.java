package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.seal.json.dto.SnowDto;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 13:49
 * @description 在fastjson中，支持一种叫做BeanToArray的映射模式。普通模式下，JavaBean映射成json object，BeanToArray模式映射为json array。
 **/
public class JsonBeanToArrayTest {

    public static void main(String[] args) {
        getBeanToArray();
    }

    private static void getBeanToArray() {
        SnowDto snowDto = new SnowDto();
        snowDto.setName("seal");
        snowDto.setAge("23");
        snowDto.setSex("男");
        snowDto.setAddress("广东省深圳市");

        String text_normal = JSON.toJSONString(snowDto);
        System.out.println("text_normal:" + text_normal);

        String text_beanToArray = JSON.toJSONString(snowDto, SerializerFeature.BeanToArray);
        System.out.println("text_beanToArray:" + text_beanToArray);

        // support beanToArray & normal mode
        JSONObject text_supportArrayToBean = JSON.parseObject(text_normal, Feature.SupportArrayToBean);
        System.out.println("text_supportArrayToBean:" + text_supportArrayToBean);
    }
}
