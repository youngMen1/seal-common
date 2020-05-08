package com.seal.features.jdk8test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author fengzhiqiang
 * @date-time 2020/5/8 13:59
 **/
public class ExeampleTest5 {

    private final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    /**
     * Optional
     */
    @Test
    public void test7() {
        pickName1(friends, "N");
        pickName2(friends, "N");
    }

    public static void pickName1(List<String> names, String startingLetter) {
        String foundName = null;
        for (String name : names) {
            if (name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName));
    }


    public static void pickName2(List<String> names, String startingLetter) {
        final Optional<String> foundName = names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
        foundName.ifPresent(System.out::println);
    }

}
