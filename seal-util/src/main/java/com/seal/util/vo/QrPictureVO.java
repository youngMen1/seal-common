package com.seal.util.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.seal.util.enums.QrTypeEnum;
import lombok.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/16 19:38
 * @description
 **/
@Data
public class QrPictureVO {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 二维规格类型
     */
    private QrTypeEnum qrTypeEnum;


    public static void main(String[] args) {
//        List list = new ArrayList();
//        int num = 0;
//        for (int i = 0; i < 10; i++) {
//            QrPictureVO qrPictureVO = new QrPictureVO();
//            qrPictureVO.setChannelName("1");
//            qrPictureVO.setQrTypeEnum(QrTypeEnum.DIMENSION_EIGHT);
//            list.add(qrPictureVO);
//            if (list.size() == 2) {
//                System.out.println("第" + ++num);
//                list.clear();
//            }
//        }
//        System.out.println("第" + list.size());

        List<BasePostParam> list = new ArrayList<>();
        BasePostParam qrPictureVO = new BasePostParam();
        qrPictureVO.setParam("1");
        qrPictureVO.setSign("1");
        list.add(qrPictureVO);

        BasePostParam qrPictureVO2 = new BasePostParam();
        qrPictureVO2.setParam("2");
        qrPictureVO2.setSign("2");
        list.add(qrPictureVO2);


        List<BasePostParam> list1 = new ArrayList<>();
        BasePostParam basePostParam = new BasePostParam();
        basePostParam.setParam("2");
        basePostParam.setSign("2");
        list1.add(basePostParam);
        //list.removeAll(list1);
        System.out.println(list);
        try {
            writeFileContext(list, "G:短信触发的用户.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将list按行写入到txt文件中
     *
     * @param strings
     * @param path
     * @throws Exception
     */
    public static void writeFileContext(List<BasePostParam> strings, String path) throws Exception {
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (BasePostParam basePostParam : strings) {
            writer.write(basePostParam.getParam() + "\r\n");
        }
        writer.close();
    }


}
