package com.shengli.low_level_sync;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by shengli on 10/21/15.
 */
public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();

    public static final int LIMIT = 100;

    private Object lock = new Object();

    private final Random random = new Random();

    public void producer() throws InterruptedException {

        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            synchronized (lock) {

                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("list size is "+list.size());

                int value = list.removeFirst();

                System.out.println("; Remove value is "+value);

                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
