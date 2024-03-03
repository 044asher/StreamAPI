package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsCreationEx {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten");

        Stream<String> stream = list.stream();
        stream.filter(x-> x.length() == 3).map(x -> x + " - the length of the letters is three").forEach(System.out::println);

        Stream<String> countStream = list.stream();
        System.out.println(countStream.filter(x -> x.length() == 3).count());

        int[] array = {1, 2, 3, 4, 5};
        IntStream streamFromArray = Arrays.stream(array);

        List<String> list1 = Stream.of("One", "Two", "Three").toList();
        System.out.println(list1);

        Stream<Integer> st = Stream.of(1, 2, 3, 4);

        Map<Integer, String> map = Map.ofEntries(
                Map.entry(1, "One"),
                Map.entry(2, "Two"),
                Map.entry(3, "Three")
        );
        Stream<Map.Entry<Integer, String>> mapStream = map.entrySet().stream();
        mapStream.forEach(System.out::println);




    }
}