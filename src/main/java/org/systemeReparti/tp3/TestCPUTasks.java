package org.systemeReparti.tp3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


    public class TestCPUTasks {
        public static void main(String[] args) throws InterruptedException {
            // Changez cette valeur pour tester différentes tailles de pool
            int Pool_size = 4;
            long end;
            long begin;
            long time = 0;

            ExecutorService executor = Executors.newFixedThreadPool(Pool_size);
            begin = System.currentTimeMillis();

            for (int j = 0; j < 100000; j++) {
                executor.submit(new CPUTask());
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                // Attendre que toutes les tâches soient terminées
            }

            end = System.currentTimeMillis();
            time = end - begin;
            System.out.println("Finished all threads");
            System.out.println("Elapsed Time: " + time + " Milliseconds");
        }

        static class CPUTask implements Runnable {
            @Override
            public void run() {
                // Appel de la fonction
                Fibonacci();
                System.out.println("Thread Name : " + Thread.currentThread().getName());
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
