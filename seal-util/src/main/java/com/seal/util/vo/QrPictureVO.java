package com.seal.util.vo;

import com.seal.util.enums.QrTypeEnum;
import lombok.Data;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/16 19:38
 * @description
 **/
@Data
public class QrPictureVO {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 二维规格类型
     */
    private QrTypeEnum qrTypeEnum;


}
