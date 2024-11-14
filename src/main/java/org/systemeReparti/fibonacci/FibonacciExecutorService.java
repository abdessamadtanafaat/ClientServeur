package org.systemeReparti.fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciExecutorService {
    private final int nombre;

    public FibonacciExecutorService(int nombre) {
        this.nombre = nombre;
    }

    private int calculElementaire(int n) {
        if (n <= 1) {
            return n;
        }
        return calculElementaire(n - 1) + calculElementaire(n - 2);
    }

    public int compute() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4); // Pool fixe de 4 threads
        Future<Integer> result = executor.submit(() -> calculElementaire(nombre));

        int finalResult = result.get(); // Récupère le résultat
        executor.shutdown();
        return finalResult;
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            FibonacciExecutorService f = new FibonacciExecutorService(45);
            int result = f.compute();

            System.out.println("Résultat final : " + result);

            long end = System.currentTimeMillis();
            System.out.println("Temps d'exécution : " + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
