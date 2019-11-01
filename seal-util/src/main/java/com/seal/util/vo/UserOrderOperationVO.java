package com.seal.util.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/10/10 11:02
 * @description 劵码
 **/
@Data
public class UserOrderOperationVO {
    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 企业编号
     */
    private String enterpriseCode;

    /**
     * 企业密钥
     */
    private String enterpriseSecretKey;

    /**
     * 会员编号
     */
    private String userNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单数量
     */
    private int orderNumber;

    /**
     * 劵码编号
     */
    private String couponCode;

    /**
     * 是否发送短信
     */
    private Boolean isSendSms;

    /**
     * 手机号集合
     */
    private List<String> smsMobileList;
}
