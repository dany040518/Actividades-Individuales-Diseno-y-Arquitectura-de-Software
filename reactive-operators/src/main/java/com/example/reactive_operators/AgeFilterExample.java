package com.example.reactive_operators;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class AgeFilterExample {
    public static void main(String[] args) {
        List<Integer> ages = Arrays.asList(16, 20, 17, 22, 25, 18, 30);

        Observable.fromIterable(ages)
                .filter(age -> age > 18) // Filtrar solo edades mayores a 18
                .map(age -> age * 2) // Multiplicar cada edad filtrada por 2
                .toList()
                .map(modifiedAges -> modifiedAges.stream().mapToInt(Integer::intValue).average().orElse(0)) // Calcular promedio
                .subscribe(average -> System.out.println("Promedio de edades modificadas: " + average));
    }

}
