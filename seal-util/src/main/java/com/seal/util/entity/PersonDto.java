package com.seal.util.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 16:48
 * @description
 **/
@Data
public class PersonDto {
    private String name;
    private String age;
    private Date birthday;

}
