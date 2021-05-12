package com.seal.features.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步编程
 * Java 在 1.8 版本提供了 CompletableFuture 来支持异步编程，
 * CompletableFuture 有可能是你见过的最复杂的工具类了，不过功能也着实让人感到震撼。
 *
 * @author fengzhiqiang
 * @date 2020/12/23 18:11
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        // 我们分了 3 个任务：任务 1 负责洗水壶、烧开水，
        // 任务 2 负责洗茶壶、洗茶杯和拿茶叶，任务 3 负责泡茶。
        // 其中任务 3 要等待任务 1 和任务 2 都完成后才能开始。
        createCompletableFuture();
    }

    private static void createCompletableFuture() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
        //等待任务3执行结果
        System.out.println(f3.join());


        // 一次执行结果：
//                T1:洗水壶...
//                T2:洗茶壶...
//                T1:烧开水...
//                T2:洗茶杯...
//                T2:拿茶叶...
//                T1:拿到茶叶:龙井
//                T1:泡茶...
//                上茶:龙井
    }

    private static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }


}
