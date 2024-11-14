package org.systemeReparti.tp5ex2;

import java.util.Random;

public class TestAsynchroneThreadsVirtuels {

    public static Integer random() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) throws InterruptedException {
        long begin, end, time = 0;
        int totalTasks = 1000;

        begin = System.currentTimeMillis();

        // Create and start virtual threads
        Thread[] threads = new Thread[totalTasks];
        final int[] sum = {0};

        for (int i = 0; i < totalTasks; i++) {
            int taskIndex = i; // For use inside the lambda expression
            threads[i] = Thread.ofVirtual().start(() -> {
                try {
                    Thread.sleep(5); // Simulate I/O
                    sum[0] += random();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        end = System.currentTimeMillis();
        time = end - begin;

        System.out.println("Total sum: " + sum[0]);
        System.out.println("Elapsed Time: " + time + " Milliseconds");
    }
}
