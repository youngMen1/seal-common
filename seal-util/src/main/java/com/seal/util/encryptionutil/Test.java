package com.seal.util.encryptionutil;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seal.util.httputil.OkHttpUtil;
import com.seal.util.vo.BasePostParam;
import com.seal.util.vo.UserOrderOperationVO;
import org.bouncycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/1 20:44
 * @description 数据签名和加密
 * URL:https://xxx.xxx.xxx/xxx/xxx.svc/zzxz
 * EnterpriseCode:GuDeFeiLi2
 * EnterpriseSecretKey:48763039841970429690
 * UserNo:55540507472214906710
 * CouponCode:CS001
 * SHA256Key:8826cebb592ca4200
 * AESKey:94af7e8db0e0cd1a
 **/
public class Test {

    public static void main(String[] args) {
        createUserOrderOperationVO();
    }

    /**
     * 1.接口加解密说明
     * 固定HMACSHA256Key：91b980153904d488  测试用
     * 固定AesKey：94af7e8db0e0cd1a 测试用
     * 随机16位向量 IV
     * 签名加密方式：请求实体类JSON（不排除空的字段）HMACSHA256加密
     * Post请求参数：随机五位数字+请求公共实体类JSON AES加密（向量 IV）+ IV 拼接成字符串
     */
    public static void createUserOrderOperationVO() {
        UserOrderOperationVO userOrderOperationVO = new UserOrderOperationVO();
        userOrderOperationVO.setOperationType("add");
        userOrderOperationVO.setEnterpriseCode("GuDeFeiLi2");
        userOrderOperationVO.setEnterpriseSecretKey("48763039841970429690");
        userOrderOperationVO.setUserNo("55540507472214906710");
        userOrderOperationVO.setOrderNo("526360894999360974598");
        userOrderOperationVO.setOrderNumber(1);
        userOrderOperationVO.setCouponCode("cs01");
        userOrderOperationVO.setIsSendSms(false);
        userOrderOperationVO.setSmsMobileList(null);

        // 转json
        String userOrderOperationVOJson = JSON.toJSONString(userOrderOperationVO);

        // 签名
        String sign = Sha256Security.hmacSha256(userOrderOperationVOJson, "8826cebb592ca4200");

        // 创建公共请求实体
        BasePostParam basePostParam = new BasePostParam();
        basePostParam.setParam(userOrderOperationVOJson);
        // 添加签名
        basePostParam.setSign(sign);
        // 转json
        String basePostParamJson = JSON.toJSONString(basePostParam);
        // 添加完签名后再加密
        String encryptBasePostParamJsonJson = AesCBCUtils.encrypt(basePostParamJson, "94af7e8db0e0cd1a");

        String json = RandomUtil.getRandom() + encryptBasePostParamJsonJson + "1234567890123456";

        System.out.println("添加完签名后再加密的数据:{}" + json);

        /**
         * 解密数据
         */
        decryptData(json);

        String result = OkHttpUtil.postJsonParams("https://xxx.xxx.xxx/xxx/xxx.svc/zzxz", json);

        String deResult = null;
        try {
            // 先url解码
            String urlDeResult = URLDecoder.decode(result, "UTF-8");
            // 再Base64解码
            deResult = new String(Base64.decode(urlDeResult), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(deResult);
        Boolean booleanResult = jsonObject.getBoolean("Result");
        if (!booleanResult) {
            System.out.println(jsonObject.getString("ReturnParam"));
        }
    }

    /**
     * 将数据解密
     *
     * @param json
     */
    private static void decryptData(String json) {
    }
}
