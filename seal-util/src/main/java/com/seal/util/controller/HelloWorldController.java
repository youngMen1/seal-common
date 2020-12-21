package com.seal.util.controller;

import java.util.Date;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.seal.util.entity.Person;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/2 14:15
 * @description
 **/
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("/get")
    public String get() {
        return "get无参请求成功";
    }

    @GetMapping("/getWithParam")
    public String getWithParam(@RequestParam String message) {
        return "get带参请求成功,参数message: " + message;
    }

    @PostMapping("/post")
    public String post(@RequestHeader("User-Agent") String userAgent,
                       @RequestHeader("Accept") String accept,
                       @RequestHeader("Accept-Language") String acceptLanguage,
                       @RequestHeader("Accept-Encoding") String acceptEncoding,
                       @RequestHeader("Cookie") String cookie,
                       @RequestHeader("Connection") String conn) {
        // 打印请求头信息
        System.out.println("Cookie = " + cookie);
        System.out.println("Connection = " + conn);
        System.out.println("Accept = " + accept);
        System.out.println("Accept-Language = " + acceptLanguage);
        System.out.println("Accept-Encoding = " + acceptEncoding);
        System.out.println("User-Agent = " + userAgent);

        return "post无参请求成功";
    }

    @PostMapping("/postWithParam")
    public String postWithParam(@RequestParam String code, @RequestParam String message) {
        return "post带参请求成功,参数code: " + code + ",参数message: " + message;
    }

    @PostMapping("/bigExcelExportSingleSheetTest")
    public static void bigExcelExportSingleSheetTest() throws IOException {
        Workbook workbook = null;
        ExportParams params = new ExportParams("大数据测试", "测试");
        for (int i = 0; i < 1; i++) {
            // 数据库分页查询结果
            // List<Person> goodsList = getGoodsListByPage(i, 10000);
            List<Person> goodsList = new ArrayList<>();
            for (int J = 0; i < 100000; i++) {
                Person person = new Person();
                person.setName("冯志强");
                person.setAge("18");
                person.setBirthday(new Date());
                goodsList.add(person);
            }
            // 数据分页导出
            workbook = ExcelExportUtil.exportBigExcel(params, Person.class, goodsList);
            goodsList.clear();
        }

        ExcelExportUtil.closeExportBigExcel();
        File saveFile = new File("excel/");
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("excel/测试数据.xlsx");
        workbook.write(fos);
        fos.close();
    }



}
