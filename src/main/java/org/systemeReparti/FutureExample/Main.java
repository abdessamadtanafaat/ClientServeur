package org.systemeReparti.FutureExample;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Future<Integer> future1 = executor.submit(new OrderFetcher());
        Future<Boolean> future2 = executor.submit(new InventoryChecker(future1.get()));
        Future<String> future3 = executor.submit(new PaymentAcceptor(future2.get()));
        System.out.println(future3.get());
        executor.shutdown();

    }
}
