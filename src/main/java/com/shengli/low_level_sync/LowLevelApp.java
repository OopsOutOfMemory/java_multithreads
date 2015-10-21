package com.shengli.low_level_sync;

/**
 * Created by shengli on 10/21/15.
 */
public class LowLevelApp {
    public static void main(String[] args) throws InterruptedException {
        final Processor p = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    p.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    p.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
