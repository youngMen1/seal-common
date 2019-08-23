package com.seal.jackson.entity;

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
}
