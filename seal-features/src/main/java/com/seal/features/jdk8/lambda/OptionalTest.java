package com.seal.features.jdk8.lambda;

import java.util.Optional;

/**
 * Optional类是Java8为了解决null值判断问题，
 * 使用Optional类可以避免显式的null值判断（null的防御性检查），
 * 避免null导致的NPE（NullPointerException）。
 *
 * @author fengzhiqiang
 * @date 2021/5/24 17:08
 **/
public class OptionalTest {


    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        getCount(testDemo);
    }

    private static void getCount(TestDemo testDemo) {
        // if判断：判断好多层
        int count1 = 1;
        if(testDemo != null){
            if(testDemo.getCount() != null){
                count1 = testDemo.getCount();
            }
        }
        System.out.println(count1);

        // 三目运算符：嵌套层数深，可读性不好
        int count2 = testDemo != null ? (testDemo.getCount() != null ? testDemo.getCount() : 1) : 1;
        System.out.println(count2);

        // Java8-Optional：优雅，可读性较好
        int count3 = Optional.ofNullable(testDemo).map(TestDemo::getCount).orElse(1);
        System.out.println(count3);
    }

    private static class TestDemo {

        private Integer count;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
