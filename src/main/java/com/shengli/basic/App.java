package com.shengli.basic;

import java.util.Scanner;

/**
 * Created by shengli on 10/20/15.
 *
 * shut down a thread in another thread
 */
public class App {
    public static void main(String[] args) {
        Processor pro1 = new Processor();
        pro1.start();

        Processor pro2 = new Processor();
        pro2.start();

        Scanner s = new Scanner(System.in);
        s.nextLine();

        pro1.shutdown();
    }
}

class Processor extends Thread {

    private volatile boolean running = true;


    public void run() {
        while (running) {
            System.out.println("Thread "+Thread.currentThread().getName()+" Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
