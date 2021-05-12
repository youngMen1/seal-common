package com.seal.features.juc.forkjoin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author fengzhiqiang
 * @date 2021/5/12 15:33
 **/
public class Video {

    private List<String> videoStream;

    /**
     * create fake video stream
     *
     * @return
     */
    List<String> getVideoStream() {
        return IntStream.range(0, 1_000_000).mapToObj(String::valueOf).collect(Collectors.toList());
    }
}
