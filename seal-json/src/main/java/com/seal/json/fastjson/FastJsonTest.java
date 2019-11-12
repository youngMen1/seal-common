package com.seal.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.seal.json.dto.SnowDto;
import com.seal.json.dto.SnowDtoGroup;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/8 14:18
 * @description
 **/
public class FastJsonTest {
    public static void main(String[] args) {
        // 构建用户geust
        SnowDto guestUser = new SnowDto();
        guestUser.setName("guest");
        guestUser.setAge("28");
        // 构建用户root
        SnowDto rootUser = new SnowDto();
        rootUser.setName("root");
        guestUser.setAge("35");
        // 构建用户组对象
        SnowDtoGroup group = new SnowDtoGroup();
        group.setName("admin");
        group.getSnowDtoList().add(guestUser);
        group.getSnowDtoList().add(rootUser);
        // 用户组对象转JSON串
        String jsonString = JSON.toJSONString(group);
        System.out.println("jsonString:" + jsonString);
        // JSON串转用户组对象
        SnowDtoGroup group2 = JSON.parseObject(jsonString, SnowDtoGroup.class);
        System.out.println("group2:" + group2);

        // 构建用户对象数组
        SnowDto[] users = new SnowDto[2];
        users[0] = guestUser;
        users[1] = rootUser;
        // 用户对象数组转JSON串
        String jsonString2 = JSON.toJSONString(users);
        System.out.println("jsonString2:" + jsonString2);
        // JSON串转用户对象列表
        List<SnowDto> users2 = JSON.parseArray(jsonString2, SnowDto.class);
        System.out.println("users2:" + users2);

    }
}
