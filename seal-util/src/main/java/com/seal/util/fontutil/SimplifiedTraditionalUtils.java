package com.seal.util.fontutil;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description 简体繁体转换工具类
 **/
@Log4j2
public final class SimplifiedTraditionalUtils {

    private SimplifiedTraditionalUtils() {
    }

    private static final String encode = "GB2312";

    /**
     * 繁体转简体
     *
     * @return
     * @author xuan
     * @date 2018.11.20
     */
    public static String convertToSimple(String str) {
        return ZhConverterUtil.convertToSimple(str);
    }


    /**
     * 简体转繁体
     *
     * @return
     * @author xuan
     * @date 2018.11.20
     */
    public static String convertToTraditional(String str) {
        return ZhConverterUtil.convertToTraditional(str);
    }


    /**
     * 判断简体还是繁体字
     *
     * @author xuan
     * @date 2018.11.28
     */
    public static boolean isSimpleOrComplex(String str) {
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                //简体
                return true;
            } else {
                //繁体
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("字符串判断是否是简体中文异常");
        }
    }

    /**
     * 判断中文还是英文
     *
     * @author xuan
     * @date 2018.11.28
     */
    public static boolean isChinneseOrEnglish(String str) {
        String reg = "[\\u4E00-\\u9FA5]+";
        Matcher m = Pattern.compile(reg).matcher(str);
        if (m.find()) {
            //中文
            return true;
        }
        //英文
        return false;
    }

    public static void main(String[] args) {
        System.out.println(convertToTraditional("强"));
    }


}
