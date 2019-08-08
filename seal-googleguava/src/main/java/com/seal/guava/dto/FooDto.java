package com.seal.guava.dto;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 16:14
 * @description
 **/
@Data
public class FooDto {
    public String sortedBy;
    @Nullable
    public int notSortedBy;

}
