package com.learning.reactiveandconcurrency.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaExample {
    public static void main(String[] args) throws InterruptedException {
        Observable.range(1, 20)
                .observeOn(Schedulers.computation())
                .filter(i -> {
                    System.out.println("Filtering " + i + " on " + Thread.currentThread().getName());
                    return i % 2 == 0;
                })
                .take(10)
                .map(i -> {
                    System.out.println("Doubling " + i + " on " + Thread.currentThread().getName());
                    return i * 2;
                })
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    System.out.println("Writing " + i + " to disk on " + Thread.currentThread().getName());
                    // Simulate writing to disk by sleeping for 100ms
                    Thread.sleep(100);
                });

        // Wait for the subscription to complete
        Thread.sleep(1000);
    }
}
