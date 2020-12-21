package com.seal.util.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

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
    @Excel(name = "姓名", needMerge = true, width = 20)
    private String name;
    @Excel(name = "年龄", needMerge = true, width = 20)
    private String age;
    @Excel(name = "出生日期", needMerge = true, width = 20)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
}
