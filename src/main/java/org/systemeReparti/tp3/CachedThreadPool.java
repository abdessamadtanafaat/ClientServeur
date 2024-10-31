package org.systemeReparti.tp3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        long begin = System.currentTimeMillis();

        for (int j = 0; j < 100000; j++) {
            executor.submit(new CPUTask());
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Attendre que toutes les tâches soient terminées
        }

        long end = System.currentTimeMillis();
        System.out.println("CachedThreadPool Elapsed Time: " + (end - begin) + " Milliseconds");
    }

    static class CPUTask implements Runnable {
        @Override
        public void run() {
            Fibonacci();
        }

        private long Fibonacci() {
            int number = 10000;
            long sum = 2;
            int fibo1 = 1, fibo2 = 1, fibonacci = 1;
            for (int i = 3; i <= number; i++) {
                fibonacci = fibo1 + fibo2;
                fibo1 = fibo2;
                fibo2 = fibonacci;
                sum = sum + fibonacci;
            }
            return sum;
        }
    }
}

