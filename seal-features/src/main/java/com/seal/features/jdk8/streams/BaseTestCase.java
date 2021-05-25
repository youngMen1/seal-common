package com.seal.features.jdk8.streams;

import com.seal.features.entity.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @author fengzhiqiang
 * @date 2021/5/25 13:45
 **/
public class BaseTestCase {

    protected static final List<String> servers = Arrays.asList("Felordcn", "Tomcat", "Jetty", "Undertow", "Resin");

    protected static final List<Employee> list = Arrays.asList(
            new Employee(1, "Alex", 1000),
            new Employee(2, "Michael", 2000),
            new Employee(3, "Jack", 1500),
            new Employee(4, "Owen", 1500),
            new Employee(5, "Denny", 2000));
}
