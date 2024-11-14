package org.systemeReparti.tp5ex2;

import java.util.Random;
import java.util.concurrent.*;

public class TestAsynchroneForkJoinPool {

    public static Integer random() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long begin, end, time = 0;
        int totalTasks = 1000;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        begin = System.currentTimeMillis();

        // Create an array of tasks to be executed in the ForkJoinPool
        ForkJoinTask<Integer>[] tasks = new ForkJoinTask[totalTasks];

        for (int i = 0; i < totalTasks; i++) {
            tasks[i] = forkJoinPool.submit(() -> {
                try {
                    Thread.sleep(5); // Simulate I/O
                    return random();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Collect the results
        int sum = 0;
        for (ForkJoinTask<Integer> task : tasks) {
            sum += task.get();  // Wait for task completion
        }

        end = System.currentTimeMillis();
        time = end - begin;

        System.out.println("Total sum: " + sum);
        System.out.println("Elapsed Time: " + time + " Milliseconds");

        forkJoinPool.shutdown();
    }
}
