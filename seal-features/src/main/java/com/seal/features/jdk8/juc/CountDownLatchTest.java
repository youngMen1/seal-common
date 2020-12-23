package com.seal.features.jdk8.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author fengzhiqiang
 * @date 2020/12/23 15:03
 **/
public class CountDownLatchTest {

    public static void main(String[] args) {

        // 对账系统在一个单线程里面循环查询订单、派送单，然后执行对账，最后将写入差异库。
        createCheckOrder();

        // 对于串行化的系统，优化性能首先想到的是能否利用多线程并行处理。
        createThreadCheckOrder();

        // 用线程池优化
        createExecutorCheckOrder();

        // 进一步优化性能,很显然，getPOrders() 和 getDOrders() 这两个查询操作和对账操作也是可以并行的
        createCyclicBarrierCheckOrder();
    }

    /**
     * 用双队列来实现完全的并行。
     */
    private static void createCyclicBarrierCheckOrder() {
//        // 订单队列
//        Vector<P> pos;
//        // 派送单队列
//        Vector<D> dos;
//        // 执行回调的线程池
//        Executor executor =
//                Executors.newFixedThreadPool(1);
//        final CyclicBarrier barrier =
//                new CyclicBarrier(2, () -> {
//                    executor.execute(() -> checkVector());
//                });
//
//        void checkVector () {
//            P p = pos.remove(0);
//            D d = dos.remove(0);
//            // 执行对账操作
//            diff = check(p, d);
//            // 差异写入差异库
//            save(diff);
//        }
//
//        void checkAll () {
//            // 循环查询订单库
//            Thread T1 = new Thread(() -> {
//                while (存在未对账订单) {
//                    // 查询订单库
//                    pos.add(getPOrders());
//                    // 等待
//                    barrier.await();
//                }
//            });
//            T1.start();
//            // 循环查询运单库
//            Thread T2 = new Thread(() -> {
//                while (存在未对账订单) {
//                    // 查询运单库
//                    dos.add(getDOrders());
//                    // 等待
//                    barrier.await();
//                }
//            });
//            T2.start();
//        }
    }

    /**
     * 但是在线程池的方案里，线程根本就不会退出，所以 join() 方法已经失效了。
     * 所以最直接的办法是引入一个计数器CountDownLatch 实现线程等待
     */
    private static void createExecutorCheckOrder() {
//       // 创建2个线程的线程池
//        Executor executor =
//                Executors.newFixedThreadPool(2);
//        while (存在未对账订单) {
//            // 计数器初始化为2
//            CountDownLatch latch =
//                    new CountDownLatch(2);
//            // 查询未对账订单
//            executor.execute(() -> {
//                pos = getPOrders();
//                // 对计数器减 1
//                latch.countDown();
//            });
//            // 查询派送单
//            executor.execute(() -> {
//                dos = getDOrders();
//                // 对计数器减 1
//                latch.countDown();
//            });
//
//            // 等待两个查询操作结束
//            latch.await();
//
//            // 执行对账操作
//            diff = check(pos, dos);
//            // 差异写入差异库
//            save(diff);
//        }
    }

    /**
     * 优化之后，基本上可以跟老板汇报收工了，但还是有点美中不足，相信你也发现了，
     * while 循环里面每次都会创建新的线程，而创建线程可是个耗时的操作。
     * 所以最好是创建出来的线程能够循环利用，估计这时你已经想到线程池了，
     * 是的，线程池就能解决这个问题。
     */
    private static void createThreadCheckOrder() {
//        while(存在未对账订单){
//            // 查询未对账订单
//            Thread T1 = new Thread(()->{
//                pos = getPOrders();
//            });
//            T1.start();
//            // 查询派送单
//            Thread T2 = new Thread(()->{
//                dos = getDOrders();
//            });
//            T2.start();
//            // 等待T1、T2结束
//            T1.join();
//            T2.join();
//            // 执行对账操作
//            diff = check(pos, dos);
//            // 差异写入差异库
//            save(diff);
//        }
    }

    private static void createCheckOrder() {

//        while(存在未对账订单){
//            // 查询未对账订单
//            pos = getPOrders();
//            // 查询派送单
//            dos = getDOrders();
//            // 执行对账操作
//            diff = check(pos, dos);
//            // 差异写入差异库
//            save(diff);
//        }
    }


}
