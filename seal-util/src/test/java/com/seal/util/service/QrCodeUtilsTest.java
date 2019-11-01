package com.seal.util.service;

import com.seal.util.qrutil.QrCodeUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/18 11:13
 * @description
 **/
public class QrCodeUtilsTest {


    /**
     * 2、测试 多个图片合成
     */
    @Test
    public void testimageAndImages() {

        String sourceFilePath = "/Users/healerjean/Desktop/origin.jpeg";
        String innerImageFilePath = "/Users/healerjean/Desktop/img.jpeg";
        // 构建叠加层
        BufferedImage buffImg = null;
        try {
            buffImg = QrCodeUtils.imageAndImages(ImageIO.read(new File(sourceFilePath)), ImageIO.read(new File(innerImageFilePath)), 238, 588, 210, 208, 1.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出水印图片
        String saveFilePath = "/Users/healerjean/Desktop/new.png";
        QrCodeUtils.saveImageToLocalDir(buffImg, saveFilePath);
    }

    /**
     * 3、测试 不带logo的二维码
     *
     * @throws Exception
     */
    @Test
    public void testWriteQRImg() {
        String text = "https://www.baidu.com/?tn=44004473_2_oem_dg";
        BufferedImage noLogoImage = QrCodeUtils.writeQRImg(text, 200, 200, 4);
        // 存储到本地
        // String saveFilePath = "/Users/healerjean/Desktop/new.png";

        String saveFilePath = "D:\\new.png";
        QrCodeUtils.saveImageToLocalDir(noLogoImage, saveFilePath);
    }


    /**
     * 4、测试 读取二维码信息
     *
     * @throws Exception
     */
    @Test
    public void testReadQRImg() throws Exception {
        //读取二维码信息
        String filePath = "/Users/healerjean/Desktop/new.png";
        BufferedImage image = ImageIO.read(new File(filePath));
        String info = QrCodeUtils.readQRImg(image);
        System.out.println(info);
    }

    /**
     * 5、测试 带logo的二维码
     *
     * @throws Exception
     */
    @Test
    public void testWriteQRImgWithLogo() throws Exception {
        String text = "http://blog.healerjean.top";
        String logoPath = "/Users/healerjean/Desktop/logo.png";
        BufferedImage logo = ImageIO.read(new File(logoPath));
        BufferedImage logoImage = QrCodeUtils.createLogoQRImg(text, 200, 200, 1, logo);

        //存储到本地
        String saveFilePath = "/Users/healerjean/Desktop/new.png";
        QrCodeUtils.saveImageToLocalDir(logoImage, saveFilePath);
    }

    /**
     * 6 测试
     *
     * @throws IOException
     */
    @Test
    public void testReduceImg() throws IOException {
        String reducePath = "/Users/healerjean/Desktop/reduce.png";
        BufferedImage originImage = ImageIO.read(new File(reducePath));
        BufferedImage reduceImg = QrCodeUtils.reduceImg(originImage, 200, 200, null);

        //存储到本地
        String saveFilePath = "/Users/healerjean/Desktop/new.png";
        QrCodeUtils.saveImageToLocalDir(reduceImg, saveFilePath);
    }


    @Test
    public void testTextWaterMark() throws IOException {
        String originPath = "/Users/healerjean/Desktop/reduce.png";
        BufferedImage originImage = ImageIO.read(new File(originPath));
        BufferedImage newImage = QrCodeUtils.textWaterMark(originImage, "healerjean", true);

        //存储到本地
        String saveFilePath = "/Users/healerjean/Desktop/new.png";
        QrCodeUtils.saveImageToLocalDir(newImage, saveFilePath);
    }

    @Test
    public void testImageWaterMark() throws IOException {
        String originPath = "/Users/healerjean/Desktop/reduce.png";
        BufferedImage originImage = ImageIO.read(new File(originPath));


        String logoPath = "/Users/healerjean/Desktop/origin.jpeg";
        BufferedImage logoImage = ImageIO.read(new File(logoPath));

        BufferedImage newImage = QrCodeUtils.imageWaterMark(originImage, logoImage, true);

        //存储到本地
        String saveFilePath = "/Users/healerjean/Desktop/new.png";
        QrCodeUtils.saveImageToLocalDir(newImage, saveFilePath);
    }
}

