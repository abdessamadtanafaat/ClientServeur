package org.systemeReparti.tp5ex2;

import java.util.Random;
import java.util.concurrent.*;

public class TestAsynchroneFuture {

    public static Integer random() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long begin, end, time = 0;
        int totalTasks = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        begin = System.currentTimeMillis();

        // List to hold Future objects
        Future<Integer>[] futures = new Future[totalTasks];

        // Submit tasks
        for (int i = 0; i < totalTasks; i++) {
            futures[i] = executor.submit(() -> {
                try {
                    Thread.sleep(5); // Simulate I/O
                    return random();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Collect results
        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();  // Wait for completion and retrieve the result
        }

        end = System.currentTimeMillis();
        time = end - begin;

        System.out.println("Total sum: " + sum);
        System.out.println("Elapsed Time: " + time + " Milliseconds");

        executor.shutdown();
    }
}
