package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class OperatorsEx {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> integerStream = numbers.stream().filter(n -> n % 2 == 0).limit(2);
        integerStream.forEach(System.out::println);

        List<String> words = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");

        words.stream()
                .filter(word -> word.length() > 5)
                .findFirst().ifPresent(System.out::println);



    }
}
