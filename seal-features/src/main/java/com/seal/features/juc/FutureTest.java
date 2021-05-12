package com.seal.features.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author fengzhiqiang
 * @date 2020/12/23 17:53
 **/
public class FutureTest {

    public static void main(String[] args) {

        // Future 接口有 5 个方法 分别是取消任务的方法 cancel()、判断任务是否已取消的方法 isCancelled()、判断任务是否已结束的方法 isDone()以及2 个获得任务执行结果的 get() 和 get(timeout, unit)

        // FutureTask 把任务和返回结果结合起来，变成一个可以有返回结果的任务进行提交
        // 下面的示例代码是将 FutureTask 对象提交给 ThreadPoolExecutor 去执行。
        createExecutorService();

        // FutureTask 对象直接被 Thread 执行的示例代码如下所示。相信你已经发现了，利用 FutureTask 对象可以很容易获取子线程的执行结果。
        createThreadService();

        // 使用Future 特性来实现烧水泡茶最优分工方案
        createFutureTaskService();

    }

    /**
     * 我们创建了两个 FutureTask——ft1 和 ft2，
     * ft1 完成洗水壶、烧开水、泡茶的任务，
     * ft2 完成洗茶壶、洗茶杯、拿茶叶的任务；
     * 这里需要注意的是 ft1 这个任务在执行泡茶任务前，
     * 需要等待 ft2 把茶叶拿来，
     * 所以 ft1 内部需要引用 ft2，并在执行泡茶之前，调用 ft2 的 get() 方法实现等待。
     * // 一次执行结果：
     * T1:洗水壶...
     * T2:洗茶壶...
     * T1:烧开水...
     * T2:洗茶杯...
     * T2:拿茶叶...
     * T1:拿到茶叶:龙井
     * T1:泡茶...
     * 上茶:龙井
     */
    private static void createFutureTaskService() {

//        // 创建任务T2的FutureTask
//        FutureTask<String> ft2
//                = new FutureTask<>(new T2Task());
//        // 创建任务T1的FutureTask
//        FutureTask<String> ft1
//                = new FutureTask<>(new T1Task(ft2));
//        // 线程T1执行任务ft1
//        Thread T1 = new Thread(ft1);
//        T1.start();
//        // 线程T2执行任务ft2
//        Thread T2 = new Thread(ft2);
//        T2.start();
//        // 等待线程T1执行结果
//        System.out.println(ft1.get());
//
//        // T1Task需要执行的任务：
//        // 洗水壶、烧开水、泡茶
//        class T1Task implements Callable<String> {
//            FutureTask<String> ft2;
//
//            // T1任务需要T2任务的FutureTask
//            T1Task(FutureTask<String> ft2) {
//                this.ft2 = ft2;
//            }
//
//            @Override
//            String call() throws Exception {
//                System.out.println("T1:洗水壶...");
//                TimeUnit.SECONDS.sleep(1);
//
//                System.out.println("T1:烧开水...");
//                TimeUnit.SECONDS.sleep(15);
//                // 获取T2线程的茶叶
//                String tf = ft2.get();
//                System.out.println("T1:拿到茶叶:" + tf);
//
//                System.out.println("T1:泡茶...");
//                return "上茶:" + tf;
//            }
//        }
//        // T2Task需要执行的任务:
//        // 洗茶壶、洗茶杯、拿茶叶
//        class T2Task implements Callable<String> {
//            @Override
//            String call() throws Exception {
//                System.out.println("T2:洗茶壶...");
//                TimeUnit.SECONDS.sleep(1);
//
//                System.out.println("T2:洗茶杯...");
//                TimeUnit.SECONDS.sleep(2);
//
//                System.out.println("T2:拿茶叶...");
//                TimeUnit.SECONDS.sleep(1);
//                return "龙井";
//            }
//        }

    }

    private static void createThreadService() {
//        // 创建FutureTask
//        FutureTask<Integer> futureTask
//                = new FutureTask<>(()-> 1+2);
//        // 创建并启动线程
//        Thread T1 = new Thread(futureTask);
//        T1.start();
//        // 获取计算结果
//        Integer result = futureTask.get();
    }

    private static void createExecutorService() {
//        // 创建FutureTask
//        FutureTask<Integer> futureTask
//                = new FutureTask<>(()-> 1+2);
//        // 创建线程池
//        ExecutorService es =
//                Executors.newCachedThreadPool();
//        // 提交FutureTask
//        es.submit(futureTask);
//        // 获取计算结果
//        Integer result = futureTask.get();
    }


}
