package org.systemeReparti.tp5ex2;

import java.util.Random;
import java.util.concurrent.*;

public class TestAsynchroneCompletableFuture {

    public static Integer random() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long begin, end, time = 0;
        int totalTasks = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        begin = System.currentTimeMillis();

        // List of CompletableFuture to hold asynchronous tasks
        CompletableFuture<Integer>[] futures = new CompletableFuture[totalTasks];

        // Submit tasks using CompletableFuture
        for (int i = 0; i < totalTasks; i++) {
            futures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5); // Simulate I/O
                    return random();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, executor);
        }

        // Wait for all futures to complete and sum the results
        int sum = 0;
        for (CompletableFuture<Integer> future : futures) {
            sum += future.get();  // Wait and retrieve the result
        }

        end = System.currentTimeMillis();
        time = end - begin;

        System.out.println("Total sum: " + sum);
        System.out.println("Elapsed Time: " + time + " Milliseconds");

        executor.shutdown();
    }
}
