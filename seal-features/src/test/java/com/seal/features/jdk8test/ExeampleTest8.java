package com.seal.features.jdk8test;


import com.seal.features.entity.PersonInfo;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Comparator
 * @author fengzhiqiang
 * @date-time 2020/5/8 14:07
 **/
public class ExeampleTest8 {

//    private void testA7(List<PersonInfo> people) {
//        final Function<PersonInfo, Integer> byAge = person -> person.getAge();
//        final Function<PersonInfo, String> byTheirName = person -> person.getName();
//        printPeople("Sorted in ascending order by age and name: ",
//                people.stream()
//                        .sorted(comparing(byAge).thenComparing(byTheirName))
//                        .collect(Collectors.toList()));
//    }
//
//    private void testA6(List<PersonInfo> people) {
//        final Function<PersonInfo, String> byName = person -> person.getName();
//        List<PersonInfo> list = people.stream()
//                .sorted(comparing(byName))
//                .collect(Collectors.toList());
//        printPeople("Sorted in ascending order by name: ", list);
//    }
//
//    private void testA5(List<PersonInfo> people) {
//        List<PersonInfo> list = people.stream()
//                .sorted((person1, person2) ->
//                        person1.getName().compareTo(person2.getName()))
//                .collect(Collectors.toList());
//        printPeople("Sorted in ascending order by name: ", list);
//    }

//    private void testA4(List<PersonInfo> people) {
//        people.stream()
//                .max(PersonInfo::ageDifference)
//                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));
//    }
//
//    private void testA3(List<PersonInfo> people) {
//        people.stream()
//                .min(PersonInfo::ageDifference)
//                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));
//    }
//
//    private void testA2(List<PersonInfo> people) {
//        Comparator<PersonInfo> compareAscending = (person1, person2) -> person1.ageDifference(person2);
//        Comparator<PersonInfo> compareDescending = compareAscending.reversed();
//        List<PersonInfo> list = people.stream().sorted(compareAscending).collect(Collectors.toList());
//        printPeople("Sorted in ascending order by age: ", list);
//
//        list = people.stream().sorted(compareDescending).collect(Collectors.toList());
//        printPeople("Sorted in descending order by age: ", list);
//    }
//
//    private void testA1(List<PersonInfo> people) {
//        List<PersonInfo> ascendingAge = people.stream().sorted((person1, person2) -> person1.ageDifference(person2))
//                .collect(Collectors.toList());
//        printPeople("Sorted in ascending order by age: ", ascendingAge);
//
//        List<PersonInfo> descendingAge = people.stream().sorted((person1, person2) -> person2.ageDifference(person1))
//                .collect(Collectors.toList());
//        printPeople("Sorted in descending order by age: ", descendingAge);
//    }
//
//    public static void printPeople(final String message, final List<PersonInfo> people) {
//        System.out.println(message);
//        people.forEach(System.out::println);
//    }

}
