package com.seal.json.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @JsonFormat(pattern = "yyyy-MM-DD")
    private LocalDate birthday;
}
