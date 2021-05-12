package com.seal.features.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join
 * Fork 对应的是分治任务模型里的任务分解，Join 对应的是结果合并。
 * Fork/Join 计算框架主要包含两部分，一部分是分治任务的线程池 ForkJoinPool，另一部分是分治任务 ForkJoinTask。
 *
 * 其中RecursiveTask代表有返回值的任务，而RecursiveAction代表没有返回值的任务。
 *
 * @author fengzhiqiang
 * @date 2020/12/29 17:14
 **/
public class ForkOrJoinTest {

    public static void main(String[] args) {
        // 案例一创建分治任务线程池
        createForkJoinPool();

        // 案例二模拟 MapReduce 统计单词数量
        createMapReduce();
    }

    private static void createForkJoinPool() {
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fib = new Fibonacci(30);
        // 启动分治任务
        Integer result = fjp.invoke(fib);
        // 输出结果
        System.out.println(result);
    }

    // 递归任务
    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            // 创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            // 等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
    /**
     * 学习 MapReduce 有一个入门程序，统计一个文件里面每个单词的数量，下面我们来看看如何用 Fork/Join 并行计算框架来实现。
     */
    private static void createMapReduce() {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        // 创建ForkJoin线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        // 创建任务
        MR mr = new MR(fc, 0, fc.length);
        // 启动任务
        Map result = fjp.invoke(mr);
        // 输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));

    }

    // MR模拟类
    private static class MR extends RecursiveTask<Map<String, Long>> {
        private String[] fc;
        private int start, end;

        // 构造函数
        MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            } else {
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                // 计算子任务，并返回合并的结果
                return merge(mr2.compute(), mr1.join());
            }
        }

        // 合并结果
        private Map<String, Long> merge(
                Map<String, Long> r1,
                Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>(16);
            result.putAll(r1);
            //合并结果
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null) {
                    result.put(k, c + v);
                } else {
                    result.put(k, v);
                }
            });
            return result;
        }

        // 统计单词数量
        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>(16);
            // 分割单词
            String[] words = line.split("\\s+");
            // 统计单词数量
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v + 1);
                } else {
                    result.put(w, 1L);
                }
            }
            return result;
        }
    }

}
