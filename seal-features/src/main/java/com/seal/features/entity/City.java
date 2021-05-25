package com.seal.features.entity;

/**
 * @author fengzhiqiang
 * @date 2021/5/25 14:18
 **/
public class City {

    /**
     * 城市名
     */
    private String name;

    /**
     * 构造器
     * @param name 城市名
     */
    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

