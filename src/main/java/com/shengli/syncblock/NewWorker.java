package com.shengli.syncblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shengli on 10/20/15.
 */
public class NewWorker {
    // 多个共享变量，多个sync方法，只有一个对象监视器
    // 创建多个对象，多个对象锁，锁多个对象

    private List list1 = new ArrayList<Integer>();
    private List list2 = new ArrayList<Integer>();

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void stageOne() { // if not sync method will got illegal state of list size
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    private void stageTwo() { // if not sync method will got illegal state of list size
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {

        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Total "+(end-start));

        System.out.println("list 1 size ->"+list1.size());
        System.out.println("list 2 size ->"+list2.size());

    }
}
