package com.seal.features.juc.config;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;


@Slf4j
public class ThreadPoolUtils {
    private static final Integer AWAIT_SM = 12000;

    public static <T> void disposeCommon(List<T> taskList, Consumer<T> consumer) {
        disposeCommon(taskList, consumer, AWAIT_SM);
    }

    /**
     * @see ThreadPoolConfig#commonThreadPool()
     */
    public static <T> void disposeCommon(List<T> taskList, Consumer<T> consumer, int awaitMs) {
        ThreadPoolExecutor commonThreadPool = SpringUtils.getBean("commonThreadPool", ThreadPoolExecutor.class);
        dispose(commonThreadPool, taskList, consumer, awaitMs);
    }

    public static <T> void disposeTimeOut(List<T> taskList, Consumer<T> consumer) {
        disposeTimeOut(taskList, consumer, AWAIT_SM);
    }

    /**
     * @see ThreadPoolConfig#timeOutThreadPool()
     */
    public static <T> void disposeTimeOut(List<T> taskList, Consumer<T> consumer, int awaitMs) {
        ThreadPoolExecutor commonThreadPool = SpringUtils.getBean("timeOutThreadPool", ThreadPoolExecutor.class);
        dispose(commonThreadPool, taskList, consumer, awaitMs);
    }

    public static <T> void disposeCreateNewPool(List<T> taskList, Consumer<T> consumer) {
        disposeCreateNewPool(taskList, consumer, AWAIT_SM);
    }

    public static <T> void disposeCreateNewPool(List<T> taskList, Consumer<T> consumer, int awaitMs) {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(
                    Math.min(ThreadPoolConfig.CORE_POOL_SIZE, taskList.size()),
                    new ThreadFactoryBuilder().setNameFormat("new-fixed-pool-%d").build());
            dispose(executorService, taskList, consumer, awaitMs);
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }


    public static <T> void dispose(ExecutorService executorService, List<T> taskList, Consumer<T> consumer, int awaitMs) {
        if (CollectionUtils.isEmpty(taskList)) {
            throw new IllegalArgumentException("taskList参数不能为null，且size必须大于0");
        }
        if (executorService == null) {
            throw new IllegalArgumentException("threadPoolExecutor参数不能为null");
        }
        if (awaitMs <= 0) {
            throw new IllegalArgumentException("awaitMs参数不能小于0");
        }

        CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
        taskList.forEach(item -> executorService.execute(() -> {
            try {
                consumer.accept(item);
            } finally {
                countDownLatch.countDown();
                resetDataSource();
            }
        }));
        try {
            boolean await = countDownLatch.await(awaitMs, TimeUnit.MILLISECONDS);
            if (!await) throw new TimeoutException("线程任务执行等待超时超时");
        } catch (InterruptedException | TimeoutException e) {
            log.error("countDownLatch.await()执行异常", e);
        }
    }

    public static <T> Callable<T> task(Callable<T> callable) {
        return () -> {
            try {
                return callable.call();
            } finally {
                resetDataSource();
            }
        };
    }

    /**
     * 重置DataSource
     */
    private static void resetDataSource() {
        //DataSourceSelecter.clearDataSource();
    }

    /**
     * List 按指定长度分割
     */
    private <T> List<List<T>> splitList(List<T> list, int groupSize) {
        return Lists.partition(list, groupSize);
    }
}
