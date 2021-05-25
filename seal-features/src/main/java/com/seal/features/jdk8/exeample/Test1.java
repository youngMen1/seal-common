package com.seal.features.jdk8.exeample;

import com.seal.features.entity.Person2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 使用的循环体字段的代码，也就是操作对象，数据结构之类的，这里是简单的 list 数据。
 *
 * @author fengzhiqiang
 * @date-time 2020/5/8 11:04
 **/
public class Test1 {

    private final List<BigDecimal> prices = Arrays.asList(
            new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("17"),
            new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
            new BigDecimal("45"), new BigDecimal("12"));

    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
    private final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
    private final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
    private final List<Person2> people = Arrays.asList(
            new Person2("20", "John"),
            new Person2("21", "Sara"),
            new Person2("21", "Jane"),
            new Person2("35", "Greg")
    );


    /**
     * 假设超过20块的话要打九折
     */
    private void test11() {
        BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            if (price.compareTo(BigDecimal.valueOf(20)) > 0) {
                totalOfDiscountedPrices = totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
            }
        }
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    private void test12() {
        final BigDecimal totalOfDiscountedPrices =
                prices.stream()
                        .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                        .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

}
