package com.seal.json.fastjson.serializefilter;


import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 12:01
 * @description Fastjson API SerializeFilter简介
 * SerializeFilter是通过编程扩展的方式定制序列化。fastjson支持6种SerializeFilter，用于不同场景的定制序列化。
 * PropertyPreFilter 根据PropertyName判断是否序列化
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
 * NameFilter 修改Key，如果需要修改Key,process返回值则可
 * ValueFilter 修改Value
 * BeforeFilter 序列化时在最前添加内容
 * AfterFilter 序列化时在最后添加内容
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
 **/
public interface PropertyFilter extends SerializeFilter {
    boolean apply(Object object, String propertyName, Object propertyValue);
}
