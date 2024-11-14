package org.systemeReparti.fibonacci;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class FibonacciForkJoin extends RecursiveTask<Integer> {
    private final int nombre;

    public FibonacciForkJoin(int n) {
        this.nombre = n;
    }

    @Override
    protected Integer compute() {
        if (nombre <= 1) {
            return nombre;
        }

        FibonacciForkJoin f1 = new FibonacciForkJoin(nombre - 1);
        f1.fork();  // Lance la première tâche en parallèle
        FibonacciForkJoin f2 = new FibonacciForkJoin(nombre - 2);

        return f2.compute() + f1.join(); // Attendre et combiner les résultats
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(); // Création d'un ForkJoinPool
        FibonacciForkJoin task = new FibonacciForkJoin(45);
        int result = pool.invoke(task); // Exécution de la tâche dans le pool

        System.out.println("Résultat final : " + result);

        long end = System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (end - start) + " ms");
    }
}

