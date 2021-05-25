package com.seal.features.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/31 14:59
 * @description
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private String name;
    private String age;
    /**
     * 所在城市
     */
    private City city;

    /**
     * 身高
     */
    private int height;

    /**
     * 构造器
     * @param city 所在城市
     * @param height 身高
     */
    public Person(City city, int height) {
        this.city = city;
        this.height = height;
    }


}
