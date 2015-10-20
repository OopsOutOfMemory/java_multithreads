package com.shengli.sync;

/**
 * Created by shengli on 10/20/15.
 */
public class SyncApp {
    private volatile int count = 0; // volatile not work AtomicInteger should be good

    // every object has a monitor , mutex, lock
    // every time a thread get a lock, and entry synchronized, finish, release it.
    // other thread should wait until
    // grantees the shared object state
    private synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        SyncApp syncApp = new SyncApp();
        syncApp.doWork();
    }

    private void doWork() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000 ; i++) {
//                    count++; // count = count + 1   get count and increment 1 set back to count
                    increment();
                }

            }
        });


        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }

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

        System.out.println("Count is "+count);
    }

}
