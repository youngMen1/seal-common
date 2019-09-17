package com.seal.util.vo;

import lombok.Getter;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/9/11 17:57
 * @description 二维码尺寸枚举
 **/
public enum QrTypeEnum {

    /**
     * 8
     */
    DIMENSION_EIGHT("1", "DIMENSION_EIGHT", "二维码边长8cm"),

    /**
     * 12
     */
    DIMENSION_TWELVE("2", "DIMENSION_TWELVE", "二维码边长12cm"),

    /**
     * 15
     */
    DIMENSION_FIFTEEN("3", "DIMENSION_FIFTEEN", "二维码边长15cm"),

    /**
     * 30
     */
    DIMENSION_THIRTY("4", "DIMENSION_THIRTY", "二维码边长30cm"),

    /**
     * 50
     */
    DIMENSION_FIFTY("5", "DIMENSION_FIFTY", "二维码边长50cm");



    /**
     * 状态
     */
    @Getter
    private final String code;

    /**
     * 类型
     */
    @Getter
    private final String name;

    /**
     * 描述
     */
    @Getter
    private final String description;


    QrTypeEnum(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public static String getCodeByCode(String name) {
        if (name == null || "".equals(name)) {
            return null;
        }
        QrTypeEnum[] values = QrTypeEnum.values();
        for (QrTypeEnum type : values) {
            if (type.getName().equals(name)) {
                return type.getCode();
            }
        }
        return null;
    }


    public static String getCodeByName(String code) {
        if (code == null) {
            return null;
        }
        QrTypeEnum[] values = QrTypeEnum.values();
        for (QrTypeEnum type : values) {
            if (type.getCode().equals(code)) {
                return type.getName();
            }
        }
        return null;
    }

    public static String getCodeByDescription(String code) {
        if (code == null) {
            return null;
        }
        QrTypeEnum[] values = QrTypeEnum.values();
        for (QrTypeEnum type : values) {
            if (type.getCode().equals(code)) {
                return type.getDescription();
            }
        }
        return null;
    }
}
