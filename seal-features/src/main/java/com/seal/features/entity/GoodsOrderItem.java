package com.seal.features.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author fengzhiqiang
 * @date 2021/5/27 17:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderItem {

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 售价
     */
    private BigDecimal sellPrice;
}
