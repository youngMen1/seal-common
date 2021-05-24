package com.seal.test.nullpointer;

import com.seal.test.entity.User;

/**
 * 反射的demo
 * https://blog.csdn.net/huangliniqng/article/details/88554510
 * @author fengzhiqiang
 * @date 2021/5/24 16:33
 **/
public class ReflectionTest {

    public static void main(String[] args) {
        User demo = new User();
        // 1.通过一个对象获得完整的包名和类名,添加一句:所有类的对象其实都是Class的实例。
        System.out.println(demo.getClass().getName());

        // 2、实例化Class类对象

    }
}
