package org.systemeReparti.fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciVirtualExecutor {
    private final int nombre;

    public FibonacciVirtualExecutor(int nombre) {
        this.nombre = nombre;
    }

    private int calculElementaire(int n) {
        if (n <= 1) {
            return n;
        }
        return calculElementaire(n - 1) + calculElementaire(n - 2);
    }

    public int compute() throws Exception {
        // Use virtual threads for this executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<Integer> result = executor.submit(() -> calculElementaire(nombre));

            return result.get(); // Retrieve the result
        }
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            FibonacciVirtualExecutor f = new FibonacciVirtualExecutor(45);
            int result = f.compute();

            System.out.println("Résultat final : " + result);

            long end = System.currentTimeMillis();
            System.out.println("Temps d'exécution : " + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
