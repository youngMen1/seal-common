package com.seal.guava.readfile;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/5 10:07
 * @description
 **/
public class ReadFile {

    public static void main(String[] args) {

        ReadFile readFile = new ReadFile();
        readFile.getFile();

    }

    public void getFile() {
        File file = new File(getClass().getResource("E:/test.txt").getFile());
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
