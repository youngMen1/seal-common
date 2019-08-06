package com.seal.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 10:41
 * @description
 **/
@Data
// 无参的构造方法
@NoArgsConstructor
// 全参的构造方法
@AllArgsConstructor
public class SnowBigDto {
    private Integer id;
    private String name;
    private String age;
    private String sex;
    private String address;
}
