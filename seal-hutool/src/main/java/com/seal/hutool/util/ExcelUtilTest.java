package com.seal.hutool.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 17:54
 * @description
 **/
public class ExcelUtilTest {

    public static void main(String[] args) {

        //excelUtilTest();
        // excelUtilPhoneTest();
    }


    @Test
    public void excelUtilPhoneTest() {
        // //默认读全部行列，还可以通过重载函数，第二个参数指定读取的Sheet  手机号.xlsx  交易成功的会员信息.xls  分销关系表数据.xls
        ExcelReader reader = ExcelUtil.getReader("G:\\交易成功的会员信息.xls");
        // 行索引  2行开始
        List<List<Object>> read = reader.read(1);
        ExcelReader reader2 = ExcelUtil.getReader("G:\\分销关系表数据.xls");

        List<String> stringList = new ArrayList<>();

        // 行索引  2行开始
        List<List<Object>> read2 = reader2.read(1);
        List<String> list = new ArrayList<>();
        for (List<Object> objects : read) {
            String memberId = objects.get(1).toString();
            stringList.add(objects.get(0)+"");
            // 第二列
            for (List<Object> objects2 : read2) {
                // 第一列
                String newUserId = objects2.get(0).toString();
                if (memberId.equals(newUserId)) {
                    // 第一列手机号
                    list.add(objects.get(0) + "");
                }
            }
        }

//
//        List<String> list1 = new ArrayList();
//        ExcelReader reader3 = ExcelUtil.getReader("G:\\已发短信的电话号码.xlsx");
//        // 行索引  2行开始
//        List<List<Object>> read3 = reader3.read(1);
//        for (List<Object> objects3 : read3) {
//            list1.add(objects3.get(0) + "");
//        }
        stringList.removeAll(list);


//        ExcelWriter writer = ExcelUtil.getWriter("toXls.xls");
//        //设置sheet名
//        writer.renameSheet("短信触达的购买用户");
//        writer.write(list);
//        writer.close();

        try {
            writeFileContext(stringList, "新的短信触发的用户.txt");
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
    public static void writeFileContext(List<String> strings, String path) throws Exception {
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String basePostParam : strings) {
            writer.write(basePostParam + "\r\n");
        }
        writer.close();
    }


    private static void excelUtilTest() {
        // 从文件中读取Excel为ExcelReader
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\手机号.xlsx"));

        List<List<Object>> read = reader.read(1);
        for (List<Object> objects : read) {

        }


        try {
            InputStream inputStream = new FileInputStream(new File("C:\\手机号.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // 通过sheet名获取
        //  reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"), "sheet1");

        // 读取大数据量的Excel
        ExcelUtil.readBySax("aaa.xlsx", 0, createRowHandler());

    }

    /**
     * 读取大数据量的Excel
     *
     * @return
     */
    private static RowHandler createRowHandler() {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
            }
        };
    }


}
