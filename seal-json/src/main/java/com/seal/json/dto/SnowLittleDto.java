package com.seal.json.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 10:41
 * @description Fastjson JSONField
 * @JSONField作用在Field时，其name不仅定义了输入key的名称，同时也定义了输出的名称。
 * @JSONField也可以直接作用在get或set方法上
 *
 * ordinal规定字段的顺序
 * 是根据fieldName的字母序进行序列化的，你可以通过ordinal指定字段的顺序
 * @JSONField(ordinal = 1)
 *
 * serialize/deserialize指定字段不序列化
 * @JSONField(serialize=false)
 *
 * format规定日期格式
 * @JSONField(format="yyyy-MM-dd HH:mm:ss")
 *
 * 使用serializeUsing制定属性的序列化类
 * JSONField支持新的定制化配置serializeUsing，可以单独对某一个类的某个属性定制序列化
 * @JSONField(serializeUsing = ModelValueSerializer.class)
 *
 * JSONField alternateNames
 * 在fastjson在1.2.21版本中提供了一个借鉴自gson的特性，支持反序列化时使用多个不同的字段名称，使用的方式是配置JSONField的alternateNames。
 * @JSONField(alternateNames = {"user", "person"})
 *
 * JSONField jsonDirect
 * JSONField支持一个新的配置项jsonDirect，它的用途是：当你有一个字段是字符串类型，里面是json格式数据，你希望直接输入，而不是经过转义之后再输出。
 * @JSONField(jsonDirect=true)
 **/
@Data
public class SnowLittleDto {
    private String id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(name = "AGE")
    private String age;
    @JSONField(ordinal = 1)
    private String sex;
    @JSONField(serialize=false)
    private String address;

    /**
     * 配置date序列化和反序列使用yyyyMMdd日期格式
     * @JSONField(format = "yyyyMMdd")
     * @JSONField(format="yyyy-MM-dd HH:mm:ss")
     */
    @JSONField(format = "yyyyMMdd")
    private Date createTime;
}
