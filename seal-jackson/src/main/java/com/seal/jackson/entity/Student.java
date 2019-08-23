package com.seal.jackson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2018/12/10 21:59
 * @description TODO
 **/
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private String name;
    private String sex;
    private double money;

}
