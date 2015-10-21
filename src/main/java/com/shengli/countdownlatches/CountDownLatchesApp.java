package com.shengli.countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shengli on 10/21/15.
 * This class enables a java thread to wait until other set of threads completes their tasks.
 * e.g. Application’s main thread want to wait, till other service threads which are responsible for starting framework services have completed started all services.
 */
public class CountDownLatchesApp {

    public static void main(String[] args) {
        // CountdownLatch 3个线程，每个线程干完活，计数器-1
        /*
        //Main thread start
        //Create CountDownLatch for N threads
        //Create and start N threads
        //Main thread wait on latch
        //N threads completes there tasks are returns
        //Main thread resume execution
         */
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(countDownLatch));
        }
        executorService.shutdown();
        try {
            // wait until count down latch is 0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished.");
        executorService.shutdown();
    }
}


class Processor implements Runnable {

    private CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Started...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }


}
