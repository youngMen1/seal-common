package com.seal.features.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/12/11 9:40
 * @description TODO
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;
    private Integer qty;
    private String price;
}
