package com.example.multi_threading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ParallelProcessingExample {

    public static void main(String[] args) {
        // Paso 1: Crear una lista de números para procesar
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Paso 2: Crear un Callable para procesar cada número
        List<Callable<Integer>> tasks = numbers.stream().map(number -> (Callable<Integer>) () -> {
            int square = number * number;
            System.out.println("Procesando el número " + number + ": " + square);
            Thread.sleep(1000); // Simula una operación costosa
            return square;
        }).collect(Collectors.toList()); // Cambiado a collect(Collectors.toList())
        
        // Paso 3: Crear un ExecutorService con un pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        try {
            // Paso 4: Ejecutar todas las tareas y obtener los resultados con Future
            List<Future<Integer>> results = executor.invokeAll(tasks);
            
            // Paso 5: Sumar los resultados procesados
            int totalSum = 0;
            for (Future<Integer> result : results) {
                totalSum += result.get(); // Obtiene el resultado de cada tarea
            }
            
            System.out.println("Suma total de los cuadrados: " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Finaliza el pool de hilos
        }
    }
}
