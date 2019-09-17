package com.seal.util.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.seal.util.qrutil.MatrixToImageWriter;
import com.seal.util.qrutil.QRCodeUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/16 14:37
 * @description 活动测试类
 **/
public class QrChannelTest {

    /**
     * 不带logo
     */
    @Test
    public void myTest() {
        try {
            String content = "https://www.baidu.com";
            String path = "E:/";

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
            File file1 = new File(path, "01.jpg");
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 带有logo测试类
     */
    @Test
    public void testLogo() {
        try {
            String text = "https://sso.gffac.net/#/login?direct=https%3A%2F%2Femp.gffac.net%2F#/index";
            QRCodeUtil.encode(text, "classpath:image/logo.png", "D:/image/", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
