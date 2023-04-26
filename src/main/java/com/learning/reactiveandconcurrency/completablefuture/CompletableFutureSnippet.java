package com.learning.reactiveandconcurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSnippet {

    public static void main(String[] args) {
        long started = System.currentTimeMillis();

        // configure CompletableFuture (not blocking)
        CompletableFuture<Integer> futureCount = createCompletableFuture();

        // continue to do other work
        System.out.println("started: " + started + ", current: " + System.currentTimeMillis());
        System.out.println("Took " + (System.currentTimeMillis() - started) + " milliseconds" );

        // now It's time to get the result
        try {
            int count = futureCount.get();
            System.out.println("CompletableFuture took " + (System.currentTimeMillis() - started) + " milliseconds" );

            System.out.println("Result " + count);
        } catch (InterruptedException | ExecutionException ex) {
            // Exceptions from the future should be handled here
            System.out.println("Error: " + ex);
        }
    }

    private static CompletableFuture<Integer> createCompletableFuture() {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        /* simulate long running task */
                        Thread.sleep(5000);
                    } catch (InterruptedException ignored) { }
                    return 20;
                });
    }
}
