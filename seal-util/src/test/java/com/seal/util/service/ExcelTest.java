package com.seal.util.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.seal.util.entity.Person;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2020/12/21 18:24
 **/
public class ExcelTest {

    @Test
    public void bigExcelExportSingleSheetTest() throws IOException {
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
        FileOutputStream fos = new FileOutputStream("excel/ExcelExportBigData.bigGoodsDataExport2.xlsx");
        workbook.write(fos);
        fos.close();
    }


}
