package com.seal.test.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * BloomFilter布隆过滤器
 *
 * @author fengzhiqiang
 * @date 2021/7/20 11:08
 **/
public class TestBloomFilter {
    /**
     * 目标数
     */
    private static int total = 1000000;
    /**
     * 过滤器
     */
    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total);

    /**
     * 1、往过滤器中放一百万个数，然后去验证这一百万个数是否能通过过滤器
     * 2、另外找一万个数，去检验漏网之鱼的数量
     *
     * @param args
     */
    public static void main(String[] args) {
        // 初始化1000000条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put(i);
        }

        // 匹配已在过滤器中的值，是否有匹配不上的
        for (int i = 0; i < total; i++) {
            if (!bf.mightContain(i)) {
                System.out.println("有坏人逃脱了~~~");
            }
        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        System.out.println("误伤的数量：" + count);
    }

}
