
package org.systemeReparti.tp3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
public class TestIOTasks {
    public static void main(String[] args) throws InterruptedException {
        int Pool_size = 4;
        long end;
        long begin;
        long time=0;
        ExecutorService executor = Executors.newFixedThreadPool(Pool_size);
        begin = System.currentTimeMillis();
        for (int j = 0; j < 100000; j++) {
            executor.submit(new IOTask());
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        end = System.currentTimeMillis();
        time = time + (end - begin);
        System.out.println("Elapsed Time: " + time + " Milliseconds");}
    static class IOTask implements Runnable {
        @Override
        public void run() {
            IOcall();
            System.out.println("Thread Name : " + Thread.currentThread().getName());
        }
        private long IOcall() {
            try {
                Thread.sleep(5); //(wait for 5 ms)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Random().nextInt();
        }
    }}