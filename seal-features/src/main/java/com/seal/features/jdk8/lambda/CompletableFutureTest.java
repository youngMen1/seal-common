package com.seal.features.jdk8.lambda;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 利用多线程优化性能这个核心方案得以实施的基础。
 * <p>
 * Java 在 1.8 版本提供了 CompletableFuture 来支持异步编程，
 * CompletableFuture 有可能是你见过的最复杂的工具类了，
 * 不过功能也着实让人感到震撼。
 *
 * 带有asyn的方法是异步执行这里的异步是不在当前线程中执行？ 比较困惑
 * 回答:在另一个新的线程执行，可以通过日志中的线程ID看出来
 *
 *
 * @author fengzhiqiang
 * @date 2020/12/23 10:40
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {

        // 演示烧水泡茶 依次执行结果：T1:洗水壶...T2:洗茶壶...T1:烧开水...T2:洗茶杯...T2:拿茶叶...T1:拿到茶叶:龙井T1:泡茶...上茶:龙井
        createCompletableFutureTest();

        // CompletionStage接口（接口里面描述串行关系）CompletionStage 接口如何描述串行关系、AND 聚合关系、OR 聚合关系以及异常处理。
        // 描述串行关系
        createCompletionStage();
        // 描述 AND 汇聚关系 主要是 thenCombine、thenAcceptBoth 和 runAfterBoth 系列的接口
        createCompletionStage();
        // 描述 OR 汇聚关系 主要是 applyToEither、acceptEither 和 runAfterEither 系列的接口
        createApplyToEither();

        // 异常处理,在异步编程里面，异常该如何处理呢？
        createException();

    }

    private static void createException() {
        CompletableFuture<Integer>
                f0 = CompletableFuture
                .supplyAsync(() -> 7 / 0)
                .thenApply(r -> r * 10)
                .exceptionally(e -> 0);
        System.out.println("捕获异常：" + f0.join());
    }

    /**
     * 示例代码展示了如何使用 applyToEither() 方法来描述一个 OR 汇聚关系。
     */
    private static void createApplyToEither() {

        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    int t = 1;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = 2;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> s);

        System.out.println("OR 汇聚关系" + f3.join());
    }

    /**
     * 通过下面的示例代码，你可以看一下 thenApply() 方法是如何使用的。
     * 首先通过 supplyAsync() 启动一个异步流程，之后是两个串行操作，整体看起来还是挺简单的。
     * 不过，虽然这是一个异步流程，但任务①②③却是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
     * CompletionStage thenApply(fn);
     * CompletionStage thenApplyAsync(fn);
     * CompletionStage thenAccept(consumer);
     * CompletionStage thenAcceptAsync(consumer);
     * CompletionStage thenRun(action);
     * CompletionStage thenRunAsync(action);
     * CompletionStage thenCompose(fn);
     * CompletionStage thenComposeAsync(fn);
     */
    private static void createCompletionStage() {
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> "Hello World")      //①
                        .thenApply(s -> s + " QQ")  //②
                        .thenApply(String::toUpperCase);//③

        // HELLO WORLD QQ
        System.out.println(f0.join());
    }

    private static void createCompletableFutureTest() {
        // 任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        // 任务2：洗茶壶->洗茶杯->拿茶叶
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
        // 任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
        // 等待任务3执行结果
        System.out.println(f3.join());
    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }

}
