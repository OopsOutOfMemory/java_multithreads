package com.shengli.syncblock;


/**
 * Created by shengli on 10/20/15.
 */
public class SyncBlockApp {
    // 多个共享变量，多个sync方法，只有一个对象监视器
    // 创建多个对象，多个对象锁，锁多个对象
    public static void main(String[] args) {
        /**
         *Starting...
         Total 5247
         list 1 size ->2000
         list 2 size ->2000
         */
//        new Worker().main();

        /**
         * Starting...
         Total 2574
         list 1 size ->2000
         list 2 size ->2000
         */
        new NewWorker().main();
    }
}
