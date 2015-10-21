package com.shengli.wait_notify;

import java.util.Scanner;

/**
 * Created by shengli on 10/21/15.
 * To be able to call notify() you need to synchronize on the same object.
 */
public class WaitNotifyApp {

    static final Processor processor = new Processor();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();


    }
}

class Processor  {
    public  void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer process running...");
            wait(); // lose control of this object mutex lock
            // wait release lock immediately
            System.out.println("Resumed...");
        }
    }

    public  void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            notify();// only be called in sync block
            // after go out of sync block, release lock
            // notify other thread but not release immediately
//            Thread.sleep(5000);
        }
    }
}
