package org.example.LambdaEx;



public class Main {
    public static void main(String[] args) {
        FuncInterface<String> reverse = (str) -> {
            String result = "";
            for (int i = str.length() - 1; i >= 0; i--)
                result += str.charAt(i);

            return result;
        };

        System.out.println("Reversed word: " + reverse.func("Hello"));


        FuncInterface<Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++)
                result *= i;

            return result;

        };
        System.out.println("Factorial of 5: " + factorial.func(5));


        FuncInterface<Integer> square = a -> a * a;
        System.out.println(square.func(2));

        FuncInterface1<Integer> summa = (a, b) -> a + b;
        FuncInterface1<Integer> sum = (a, b) -> a * b;

        System.out.println(summa.func(4, 2));
        System.out.println(sum.func(4, 2));
    }
}


