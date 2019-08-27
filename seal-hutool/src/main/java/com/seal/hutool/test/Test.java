package com.seal.hutool.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/27 17:22
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        httpUtil();
    }


    /**
     * HttpUtil.encode和HttpUtil.decode 两个方法封装了JDK的URLEncoder.encode和URLDecoder.decode方法，
     * 可以方便的对URL参数进行URL编码和解码。
     * HttpUtil.toParams和HttpUtil.decodeParams 两个方法是将Map参数转为URL参数字符串和将URL参数字符串转为Map对象
     * HttpUtil.urlWithForm是将URL字符串和Map参数拼接为GET请求所用的完整字符串使用
     */
    private static void httpUtil() {
        // POST请求例子：
        HashMap<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("city", "北京");
        String result = HttpUtil.post("https://www.baidu.com", paramMap);
        System.out.println("result:" + result);


        // 文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("file", FileUtil.file("D:\\a.jpeg"));
        String result2 = HttpUtil.post("https://www.baidu.com", paramMap);
        System.out.println("result2:" + result2);

    }

}
