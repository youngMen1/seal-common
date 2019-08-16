package com.seal.util.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description 对象处理工具类
 **/
public class ObjectConvertFilterUtil {

    /**
     * 获取为空属性
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }

        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
