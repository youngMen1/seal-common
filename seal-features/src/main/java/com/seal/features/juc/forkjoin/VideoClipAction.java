package com.seal.features.juc.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author fengzhiqiang
 * @date 2021/5/12 15:34
 **/
public class VideoClipAction extends RecursiveTask<Boolean> {
    /**
     * 长度
     */
    private int length;

    /**
     * 视频流
     */
    private List<String> videoStream;

    VideoClipAction(Video video, int length) {
        this.length = length;
        this.videoStream = video.getVideoStream();
    }

    @Override
    protected Boolean compute() {
        List<String> clip;
        // 创建具有给定长度的剪辑
        try {
            clip = videoStream.subList(length, length + 1000);
        } catch (Exception e) {
            clip = videoStream.subList(length, videoStream.size() - 1);
        }
        System.out.println(Thread.currentThread() +
                "\n\tVideo clip from: " + clip.get(0) + " to " + clip.get(clip.size() - 1) + "\n");
        return Boolean.TRUE;
    }
}
