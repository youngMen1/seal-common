package com.seal.hutool.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 17:54
 * @description
 **/
public class ExcelUtilTest {

    public static void main(String[] args) {

        excelUtilTest();
    }


    private static void excelUtilTest() {
        // 从文件中读取Excel为ExcelReader
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"));

        // 通过sheet名获取
        reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"), "sheet1");

        // 读取大数据量的Excel
        ExcelUtil.readBySax("aaa.xlsx", 0, createRowHandler());

    }

    /**
     * 读取大数据量的Excel
     * @return
     */
    private static RowHandler createRowHandler () {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
            }
        };
    }


}
