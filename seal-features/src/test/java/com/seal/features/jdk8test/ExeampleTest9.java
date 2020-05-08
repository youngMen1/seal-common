package com.seal.features.jdk8test;

import com.seal.features.entity.PersonInfo;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * 收集器
 * @author fengzhiqiang
 * @date-time 2020/5/8 14:12
 **/
public class ExeampleTest9 {

    private final List<PersonInfo> people = Arrays.asList(
            new PersonInfo("John", 20),
            new PersonInfo("Sara", 21),
            new PersonInfo("Jane", 21),
            new PersonInfo("Greg", 35)
    );

    /**
     * 收集器
     */
    @Test
    public void testB() {
        testB1();
        testB2();
        testB3();
        testB4();
        testB5();
        testB6();
    }

    private void testB6() {
        Comparator<PersonInfo> byAge = Comparator.comparing(PersonInfo::getAge);
        Map<Character, Optional<PersonInfo>> oldestPersonOfEachLetter =
                people.stream()
                        .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                                Collectors.reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter:");
        System.out.println(oldestPersonOfEachLetter);
    }

    @Test
    public void testB5() {
        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                        .collect(
                                Collectors.groupingBy(PersonInfo::getAge, Collectors.mapping(PersonInfo::getName, Collectors.toList())));

        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    /**
     * to Map<Integer, List<Person>>
     */
    @Test
    public void testB4() {
        Map<Integer, List<PersonInfo>> peopleByAge =
                people.stream()
                        .collect(Collectors.groupingBy(PersonInfo::getAge));
        System.out.println("Grouped by age: " + peopleByAge);
    }

    /**
     * to list
     */
    private void testB3() {
        List<PersonInfo> olderThan20 =
                people.stream()
                        .filter(person -> person.getAge() > 20)
                        .collect(Collectors.toList());
        System.out.println("People older than 20: " + olderThan20);
    }

    /**
     * to list
     */
    private void testB2() {
        List<PersonInfo> olderThan20 =
                people.stream()
                        .filter(person -> person.getAge() > 20)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20);
    }

    /**
     * to list
     */
    private void testB1() {
        List<PersonInfo> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(person -> olderThan20.add(person));
        System.out.println("People older than 20: " + olderThan20);
    }
}
