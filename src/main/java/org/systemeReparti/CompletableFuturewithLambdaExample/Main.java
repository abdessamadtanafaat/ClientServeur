package org.systemeReparti.CompletableFuturewithLambdaExample;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Step 1: Fetch Order with CompletableFuture
        CompletableFuture<Integer> orderFuture = CompletableFuture.supplyAsync(() -> {
            fetcherCall(300);
            int orderId = new Random().nextInt(10);
            System.out.println("Fetched OrderID: " + orderId);
            System.out.println(Thread.currentThread().getName());
            return orderId;
        });

        // Step 2: Check Inventory (dependent on Fetch Order)
        CompletableFuture<Boolean> inventoryFuture = orderFuture.thenApplyAsync(orderId -> {
            checkerCall(150);
            boolean isInventoryAvailable = orderId % 2 == 0;
            System.out.println("Inventory available for OrderID " + orderId + ": " + isInventoryAvailable);
            return isInventoryAvailable;
        });

        // Step 3: Accept Payment (dependent on Check Inventory)
        CompletableFuture<String> paymentFuture = inventoryFuture.thenApplyAsync(isInventoryAvailable -> {
            paymentCall(200);
            String paymentResult = isInventoryAvailable ? "Payment Accepted" : "Payment Not Accepted";
            System.out.println("Payment Result: " + paymentResult);
            return paymentResult;
        });

        // Wait for the final result of the payment step
        String finalResult = paymentFuture.get();
        System.out.println("Final Result: " + finalResult);
    }

    // Simulated delay for fetching order
    private static void fetcherCall(int msTime) {
        try {
            Thread.sleep(msTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // Simulated delay for checking inventory
    private static void checkerCall(int msTime) {
        try {
            Thread.sleep(msTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // Simulated delay for accepting payment
    private static void paymentCall(int msTime) {
        try {
            Thread.sleep(msTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
