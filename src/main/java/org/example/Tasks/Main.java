package org.example.Tasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Carl", "Urban", 52, "Male"),
                new User("Addison", "Rae", 23, "Female"),
                new User("Emma", "Stone", 37, "Female"),
                new User("Antony", "Starr", 48, "Male"),
                new User("Margo", "Robby", 30, "Female"),
                new User("John", "Doe", 15, "Male"),
                new User("Alison", "Caddy", 14, "Female"),
                new User("Teen", "Ager", 18, "Female")));

        System.out.println("Task 5.1\n-----------------------------------");
        List<String> firstThreeNames = users.stream()
                .sorted(Comparator.comparing(User::getFirstName))
                .filter(user -> user.getAge() > 25)
                .limit(3)
                .map(User::getFirstName)
                .toList();

        firstThreeNames.forEach(System.out::println);

        System.out.println("\n\nTask 5.2\n--------------------------------------");
        //1
        System.out.println("At least one under 18?: " + users.stream().anyMatch(user -> user.getAge() < 18));

        //2
        System.out.println("All users have at least 2 letters in name: " + users.stream().allMatch(user -> user.getFirstName().length() > 1));

        //3
        System.out.println("Do we have users older than 80?: " + users.stream().anyMatch(user -> user.getAge() > 80));

        //4
        Optional<User> youngestUser = users.stream().min(Comparator.comparingInt(User::getAge));
        youngestUser.ifPresent(user -> System.out.println("The name of the youngest user is: " + user.getFirstName() + " and his/her age is: " + user.getAge()));

        //5
        Optional<User> oldestUser = users.stream().max(Comparator.comparingInt(User::getAge));
        oldestUser.ifPresent(user -> System.out.println("The name of the oldest user is: " + user.getFirstName() + " and his/her age is: " + user.getAge()));

        //6
        long countOf18Aged = users.stream().filter(user -> user.getAge() == 18).count();
        System.out.println("Count of 18 aged users: " + countOf18Aged);

        //7
        Map<Boolean, List<User>> agedMap = users.stream().collect(Collectors.groupingBy(user -> user.getAge() >= 18));
        System.out.println("Adult: " + agedMap.get(true));
        System.out.println("Underage: " + agedMap.get(false));


        System.out.println("\n\nTask 5.3\n--------------------------------------");
        users.addAll(Arrays.asList(
                new User("Carl", "Urban", 52, "Male"),
                new User("Emma", "Stone", 37, "Female")));
        Stream<User> userStream1 = users.stream();
        System.out.println("All users:");
        userStream1.forEach(user -> System.out.print(user + " "));


        Stream<User> userStream = users.stream();
        List<User> distinctUsers = userStream.distinct().toList();
        System.out.println("\n\nDistinct users: ");
        distinctUsers.forEach(user -> System.out.print(user + " "));

        System.out.println("\n\n\n");
    }
}
