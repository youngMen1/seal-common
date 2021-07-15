package com.seal.features.juc.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    public static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 4;
    public static final int BLOCKING_QUEUE_SIZE = 5000;

    @Bean("commonThreadPool")
    public ThreadPoolExecutor commonThreadPool() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(BLOCKING_QUEUE_SIZE),
                new ThreadFactoryBuilder().setNameFormat("common-pool-%d").build());
    }


    @Bean("timeOutThreadPool")
    public ThreadPoolExecutor timeOutThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(BLOCKING_QUEUE_SIZE),
                new ThreadFactoryBuilder().setNameFormat("time-out-pool-%d").build());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}
