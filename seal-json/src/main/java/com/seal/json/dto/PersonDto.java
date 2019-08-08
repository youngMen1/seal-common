package com.seal.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:34
 * @description
 **/
@Data
// 无参的构造方法
@NoArgsConstructor
// 全参的构造方法
@AllArgsConstructor
public class PersonDto {
    private String id;
    private String name;
    private int age;
}
