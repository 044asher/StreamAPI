package org.example;

import java.util.Optional;

public class OptionalEx {
    public static void main(String[] args) {
        String word = "word";
        Optional<String> optionalWord = Optional.ofNullable(word);

        optionalWord.ifPresent(s -> System.out.println("Word: " + s));

        String retrievedWord = optionalWord.orElse("Unknown");
        System.out.println("Retrieved word: " + retrievedWord);

        Optional<String> upperCaseWord = optionalWord.map(String::toUpperCase);
        System.out.println("Upper case word: " + upperCaseWord.orElse("Unknown"));

        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Is optional present? " + emptyOptional.isPresent());
        System.out.println(emptyOptional.orElse("Really Empty"));

        Integer ar = 2016;
        Optional<Integer> op = Optional.of(ar);
        boolean is2016 = op.filter(y -> y == 2022).isPresent();
        System.out.println(is2016);


        Headphones headphones = new Headphones(12.0);
        System.out.println("\n\n" + headphones.priceIsInRange(headphones));

        System.out.println(headphones.priceInRangeOptional(headphones));


    }
}


class Headphones {
    private Double price;

    public Headphones(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public boolean priceIsInRange(Headphones headphones) {
        boolean isInRange = false;

        if (headphones != null && headphones.getPrice() != null
                && (headphones.getPrice() >= 10
                && headphones.getPrice() <= 15)) {

            isInRange = true;
        }
        return isInRange;
    }

    public boolean priceInRangeOptional(Headphones headphones) {
        return Optional.ofNullable(headphones).map(Headphones::getPrice)
                .filter(x -> x >= 10)
                .filter(x -> x <= 15)
                .isPresent();
    }
}
