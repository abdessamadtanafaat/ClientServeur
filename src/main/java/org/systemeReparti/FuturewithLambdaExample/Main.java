package org.systemeReparti.FuturewithLambdaExample;

import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Integer> future1 = executor.submit(() -> {
            sleep(300);
            return new Random().nextInt(6);
        });
        Integer order = future1.get();
        Future<Boolean> future2 = executor.submit(() -> {
            sleep(150);
            if (order%2==0) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        });

        Boolean isinventoryPresent = future2.get();
        Future future3 = executor.submit(() -> {
            sleep(200);
            if (isinventoryPresent)
                return "Payment accepted";
            else
                return "Payment not accepted";
        });
        System.out.println(future3.get());


    }
}
