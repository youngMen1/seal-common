package com.seal.features.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author fengzhiqiang
 * @date 2021/5/12 15:33
 **/
public class ForkJoinDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinVideo forkJoinVideo = new ForkJoinVideo(new Video());
        Future<Void> f = ForkJoinPool.commonPool().submit(forkJoinVideo);
        f.get();
    }
}
