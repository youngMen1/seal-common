package com.seal.features.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 1.问题
 * 不久前听说小明要做一个询价应用，这个应用需要从三个电商询价，
 * 然后保存在自己的数据库里。核心示例代码如下所示，
 * 由于是串行的，所以性能很慢，你来试着优化一下吧。
 * // 向电商S1询价，并保存
 * r1 = getPriceByS1();
 * save(r1);
 * // 向电商S2询价，并保存
 * r2 = getPriceByS2();
 * save(r2);
 * // 向电商S3询价，并保存
 * r3 = getPriceByS3();
 * save(r3);
 * <p>
 * 2.问题地址转坐标的服务
 * 例如你需要提供一个地址转坐标的服务，为了保证该服务的高可用和性能，你可以并行地调用 3 个地图服务商的 API，
 * 然后只要有 1 个正确返回了结果 r，那么地址转坐标这个服务就可以直接返回 r 了。
 *
 * @author fengzhiqiang
 * @date 2020/12/23 11:45
 **/
public class CompletionServiceTest {


    public static void main(String[] args) {
        // 询价应用优化方案一 采用“ThreadPoolExecutor+Future”的方案
        createOptionOne();

        // 询价应用优化方案二 当有多个阻塞操作串行的时候，前面的阻塞操作会阻塞后面的操作，可以使用阻塞队列解决
        createBlockingQueue();

        // 询价应用优化方案三
        // 不过在实际项目中，并不建议你用优化方案二这样做，因为 Java SDK 并发包里已经提供了设计精良的 CompletionService。
        // 利用 CompletionService 不但能帮你解决先获取到的报价先保存到数据库的问题，而且还能让代码更简练。
        // CompletionService 的实现原理也是内部维护了一个阻塞队列，当任务执行结束就把任务的执行结果加入到阻塞队列中，不同的是 CompletionService 是把任务执行结果的 Future 对象加入到阻塞队列中
        createCompletionService();

        // Dubbo 中有一种叫做 Forking 的集群模式，这种集群模式下，支持并行地调用多个查询服务，只要有一个成功返回结果，整个服务就可以返回了。
        // 例如你需要提供一个地址转坐标的服务，为了保证该服务的高可用和性能，你可以并行地调用 3 个地图服务商的 API，然后只要有 1 个正确返回了结果 r，那么地址转坐标这个服务就可以直接返回 r 了。
        createForking();
    }

    /**
     * geocoder(addr) {
     * //并行执行以下3个查询服务，
     * r1=geocoderByS1(addr);
     * r2=geocoderByS2(addr);
     * r3=geocoderByS3(addr);
     * //只要r1,r2,r3有一个返回
     * //则返回
     * return r1|r2|r3;
     * }
     */
    private static void createForking() {
//        // 创建线程池
//        ExecutorService executor =
//                Executors.newFixedThreadPool(3);
//        // 创建CompletionService
//        CompletionService<Integer> cs =
//                new ExecutorCompletionService<>(executor);
//        // 用于保存Future对象
//        List<Future<Integer>> futures =
//                new ArrayList<>(3);
//        //提交异步任务，并保存future到futures
//        futures.add(
//                cs.submit(() -> geocoderByS1()));
//        futures.add(
//                cs.submit(() -> geocoderByS2()));
//        futures.add(
//                cs.submit(() -> geocoderByS3()));
//        // 获取最快返回的任务执行结果
//        Integer r = 0;
//        try {
//            // 只要有一个成功返回，则break
//            for (int i = 0; i < 3; ++i) {
//                r = cs.take().get();
//                //简单地通过判空来检查是否成功返回
//                if (r != null) {
//                    break;
//                }
//            }
//        } finally {
//            //取消所有任务
//            for (Future<Integer> f : futures)
//                f.cancel(true);
//        }
//        // 返回结果
//        return r;
    }

    private static void createCompletionService() {
//        // 创建线程池
//        ExecutorService executor =
//                Executors.newFixedThreadPool(3);
//        // 创建CompletionService
//        CompletionService<Integer> cs = new
//                ExecutorCompletionService<>(executor);
//        // 异步向电商S1询价
//        cs.submit(() -> getPriceByS1());
//        // 异步向电商S2询价
//        cs.submit(() -> getPriceByS2());
//        // 异步向电商S3询价
//        cs.submit(() -> getPriceByS3());
//        // 将询价结果异步保存到数据库
//        for (int i = 0; i < 3; i++) {
//            Integer r = cs.take().get();
//            executor.execute(() -> save(r));
//        }
    }

    /**
     * 下面的示例代码展示了如何利用阻塞队列实现先获取到的报价先保存到数据库。
     */
    private static void createBlockingQueue() {
//        // 创建线程池
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        // 创建阻塞队列
//        BlockingQueue<Integer> bq =
//                new LinkedBlockingQueue<>();
//        //电商S1报价异步进入阻塞队列
//        executor.execute(() ->
//                bq.put(f1.get()));
//        //电商S2报价异步进入阻塞队列
//        executor.execute(() ->
//                bq.put(f2.get()));
//        //电商S3报价异步进入阻塞队列
//        executor.execute(() ->
//                bq.put(f3.get()));
//        //异步保存所有报价
//        for (int i = 0; i < 3; i++) {
//            Integer r = bq.take();
//            executor.execute(() -> save(r));
//        }
    }

    /**
     * 优化方案一 采用“ThreadPoolExecutor+Future”的方案
     * 缺点:当有多个阻塞操作串行的时候，前面的阻塞操作会阻塞后面的操作
     */
    private static void createOptionOne() {
//        // 创建线程池
//        ExecutorService executor =
//                Executors.newFixedThreadPool(3);
//        // 异步向电商S1询价
//        Future<Integer> f1 =
//                executor.submit(
//                        () -> getPriceByS1());
//        // 异步向电商S2询价
//        Future<Integer> f2 =
//                executor.submit(
//                        () -> getPriceByS2());
//        // 异步向电商S3询价
//        Future<Integer> f3 =
//                executor.submit(
//                        () -> getPriceByS3());
//
//        // 获取电商S1报价并保存
//        r = f1.get();
//        executor.execute(() -> save(r));
//
//        // 获取电商S2报价并保存
//        r = f2.get();
//        executor.execute(() -> save(r));
//
//        // 获取电商S3报价并保存
//        r = f3.get();
//        executor.execute(() -> save(r));
    }


}
