package com.seal.json.dto;

import lombok.Data;

import java.util.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:23
 * @description
 **/
@Data
public class FooDto {

    private String vString = "vStringhehhehe";
    private char vchar = 'x';

    private byte vbyte = 64;
    private short vshort = 128;
    private int vint = 65535;
    private long vlong = 9999999L;

    private float vfloat = 12.1f;
    private double vdouble = 22.203d;

    private boolean vboolean = false;

    private Date dddd = new Date();
    private Date vDate = new Date();
    private Date v_Date = new Date();
    private Object vnull = null;

    private String[] avString = {"aaa", "bbb", "ccc"};
    private int[] avint = {1, 2, 3, 4};
    private boolean[] avboolean = {true, false, true, true};

    private List<String> listString = new ArrayList<String>();
    private Map<String, String> map = new HashMap<String, String>();

    private BarDto barDto = new BarDto();
    private BarDto[] avBarDto = {new BarDto(), new BarDto()};
    private List<BarDto> listBarDto = new ArrayList<BarDto>();

    {
        listString.add("listString1");
        listString.add("listString2");
        listString.add("listString3");

        map.put("x", "s11111x");
        map.put("y", "s22222y");
        map.put("z", "s33333z");

        listBarDto.add(new BarDto());
        listBarDto.add(new BarDto());
        listBarDto.add(new BarDto());
    }
}
