package com.seal.util.util;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * EasyPoi实现查询数据库并导出到Excel中包含大批量导出
 * https://zhuanlan.zhihu.com/p/45654141
 * 总结:
 * 1.大数据批量导出强制使用xssf版本的Excel 
 * @author fengzhiqiang
 * @date 2020/12/21 13:04
 **/
public class ExcelUtil {

    /**
     * 基于注解的方式
     * 一个excel 创建多个sheet
     *
     * @param list
     *            多个Map key title 对应表格Title key entity 对应表格对应实体 key data
     *            Collection 数据
     * @return
     */
    public static Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
        Workbook workbook = getWorkbook(type,0);
        for (Map<String, Object> map : list) {
            ExcelExportService service = new ExcelExportService();
            service.createSheetForMap(workbook, (ExportParams)map.get("title"), (List<ExcelExportEntity>)map.get("entity"), (Collection<?>)map.get("data"));
        }
        return workbook;
    }

    /**
     * 基于编码的方式
     * 实现一个excel 创建多个sheet
     * entity 对应表格对应实体 key data Collection 数据
     * @param entity
     * @param entityList
     * @param dataSet
     * @return
     */
    public static Workbook exportExcel(ExportParams entity, List<ExcelExportEntity> entityList, Collection<?> dataSet) {
        Workbook workbook = getWorkbook(entityList.size());
        ExcelExportService service = new ExcelExportService();
        service.createSheetForMap(workbook,entity,entityList,dataSet);
        return workbook;
    }

    /**
     * 当数据量超出65536条后，在使用HSSFWorkbook或XSSFWorkbook，
     * 程序会报OutOfMemoryError：Javaheap space;内存溢出错误。
     * 这时应该用SXSSFworkbook。
     * @param size
     * @return
     */
    private static Workbook getWorkbook(ExcelType type, int size) {
        if (ExcelType.HSSF.equals(type)) {
            return new HSSFWorkbook();
        } else if (size < 100000) {
            return new XSSFWorkbook();
        } else {
            return new SXSSFWorkbook();
        }
    }

    /**
     * sheetlist的大小
     * @param size
     * @return
     */
    private static Workbook getWorkbook(int size) {
        if (size < 56636) {
            return new HSSFWorkbook();
        } else if (size < 100000) {
            return new XSSFWorkbook();
        } else {
            return new SXSSFWorkbook();
        }
    }



}
