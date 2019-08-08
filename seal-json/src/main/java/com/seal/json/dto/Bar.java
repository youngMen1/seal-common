package com.seal.json.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import lombok.Data;

import java.util.Date;
import java.util.Random;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:24
 * @description
 **/
@Data
public class Bar {
    public static SerializeConfig mapping = new SerializeConfig();
    private String barName;
    private int barAge;
    private Date barDate = new Date();

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }

    {
        Random r = new Random();
        barName = "sss_" + String.valueOf(r.nextFloat());
        barAge = r.nextInt();
    }



    public static void main(String[] args) {
        Object obj = JSON.toJSON(new Bar());
        String x1 = JSON.toJSONString(new Bar(), true);
        System.out.println(x1);
        String x2 = JSON.toJSONString(new Bar(), mapping);
        System.out.println(x2);
    }

}
