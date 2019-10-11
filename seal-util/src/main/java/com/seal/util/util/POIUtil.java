package com.seal.util.util;


import com.seal.util.dateutil.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description excel读写工具
 **/
@Slf4j
public class POIUtil {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";


    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            log.error(" 文件不存在！");
            // throw new FileImportException(" 文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            log.error(fileName + " 不是excel文件");
            //  throw new FileImportException(fileName + " 不是excel文件");
        }
    }


    public static String getFileName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }


    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (!HSSFDateUtil.isCellDateFormatted(cell)) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            // 数字
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = DateTimeUtils.formatDateLine(cell.getDateCellValue());
                } else {
                    String str = cell.getStringCellValue();
                    if (StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str) && str.contains(",")) {
                        str = str.replaceAll(",", "");
                    }
                    cellValue = String.valueOf(str);
                }
                break;
            // 字符串
            case Cell.CELL_TYPE_STRING:
                String str = cell.getStringCellValue();
                if (StringUtils.isNotEmpty(str) && StringUtils.isNotBlank(str) && str.contains(",")) {
                    str = str.replaceAll(",", "");
                }
                cellValue = String.valueOf(str);
                break;
            // Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:

         /*       try {
                    cellValue = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                log.info(cellValue + "zhi++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");*/
                cellValue = String.valueOf(cell.getCellFormula());//获取公式
                //cellValue = String.valueOf(cell.getNumericCellValue());获取值
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;

            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        String pattern = "^[\\+\\-]?[\\d]+(\\.[\\d]+)?$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isValidDate1(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意dd/MM/yyyy区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isValidDate2(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
