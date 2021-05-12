package com.seal.features.juc.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author fengzhiqiang
 * @date 2021/5/12 15:39
 **/
public class ForkJoinVideo extends RecursiveAction {

    private Video video;

    ForkJoinVideo(Video video) {
        this.video = video;
    }

    private List<VideoClipAction> getClips() {

        List<VideoClipAction> subtasks = new ArrayList<>();

        int random = (int) (Math.random() * 10 + 1);

        System.out.println("Starting " + random + " random clips...\n");

        for (int i = 0; i < random; i++) {
            subtasks.add(new VideoClipAction(video, (int) (Math.random() * 1000 + 1)));
        }
        return subtasks;
    }

    @Override
    protected void compute() {
        if (video.getVideoStream().size() >= 1000) {
            ForkJoinTask.invokeAll(getClips());
        } else {
            System.out.println(Thread.currentThread() + " : No clipping possible.");
        }
    }

}
