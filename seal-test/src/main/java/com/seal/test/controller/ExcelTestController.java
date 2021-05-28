package com.seal.test.controller;

import com.seal.test.entity.User;
import org.apache.commons.collections.list.AbstractLinkedList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

/**
 * @author fengzhiqiang
 * @date 2021/4/30 11:28
 **/
public class ExcelTestController {


    public static void main(String[] args) {
        BigDecimal ratio = new BigDecimal(30);
        BigDecimal ratioResult = ratio.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(ratioResult);

        BigDecimal b = BigDecimal.valueOf(0.111);
        BigDecimal result = b.multiply(new BigDecimal(524)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(result);


        List<User> list = new ArrayList<>();
        list.add(new User(1, new BigDecimal(100)));

        list.add(new User(5, null));
        System.out.println(list.stream().map(User::getSellPrice)
                .filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, HALF_UP));

    }


}
