package com.seal.guava.guavaobject;

import com.google.common.base.Objects;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 17:12
 * @description
 **/
public class GuavaObject {
    public static void main(String[] args) {
        guavaObjects();
    }

    private static void guavaObjects() {
        // returns true
        System.out.println(Objects.equal("a", "a"));
        // returns false
        System.out.println(Objects.equal(null, "a"));
        // returns false
        System.out.println(Objects.equal("a", null));
        // returns true
        System.out.println(Objects.equal(null, null));
    }


}
