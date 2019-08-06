package com.seal.guava.util;

import lombok.Data;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 10:41
 * @description
 **/
@Data
public class RegionBeanTree {
    private String code;
    private String pid;
    private String name;
    private List<RegionBeanTree> children;
}
