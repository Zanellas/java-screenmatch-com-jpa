package br.com.alura.screenmatch.exercicios1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Um {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("10", "abc", "20", "30x");
        List<Integer> result = input.stream()
                .map(str -> {
                    try {
                        return Optional.of(Integer.parseInt(str));
                    } catch (NumberFormatException e) {
                        return Optional.<Integer>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println(result);

    }
}
