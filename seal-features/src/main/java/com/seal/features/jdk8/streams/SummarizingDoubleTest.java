package com.seal.features.jdk8.streams;

import com.seal.features.entity.Employee;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DoubleSummaryStatistics : 用于收集统计信息(例如计数，最小值，最大值，总和、平均值)的状态对象。
 *
 * @author fengzhiqiang
 * @date 2021/5/25 13:34
 **/
public class SummarizingDoubleTest {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(
                new Employee(1, "Alex", 1000),
                new Employee(2, "Michael", 2000),
                new Employee(3, "Jack", 1500),
                new Employee(4, "Owen", 1500),
                new Employee(5, "Denny", 2000));

        // 实现方式一
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        // DoubleSummaryStatistics{count=5, sum=8000.000000, min=1000.000000, average=1600.000000, max=2000.000000}
        System.out.println(statistics);

        // 实现方式二
        DoubleSummaryStatistics statistics1 = list.stream().mapToDouble(Employee::getSalary).summaryStatistics();

        // DoubleSummaryStatistics{count=5, sum=8000.000000, min=1000.000000, average=1600.000000, max=2000.000000}
        System.out.println(statistics1);
    }

}
