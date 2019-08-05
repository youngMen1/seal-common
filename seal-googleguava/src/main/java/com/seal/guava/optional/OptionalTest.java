package com.seal.guava.optional;

import com.google.common.base.Optional;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 13:46
 * @description
 **/
public class OptionalTest {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        Optional<Integer> possible = Optional.of(5);
        // returns true
        possible.isPresent();
        // returns 5
        possible.get();
    }


}
