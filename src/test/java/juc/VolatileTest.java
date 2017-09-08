package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatileTest {

    private static Logger LOG = LoggerFactory.getLogger(VolatileTest.class);

    public static void main(String[] args) {

        ThreadDemo td = new ThreadDemo();

        new Thread(td, " my thread ").start();

        while (true) {
            if (td.isFlag()) {
                LOG.info("--------------");
                break;
            }
        }

    }

}


class ThreadDemo implements Runnable {

    private static Logger LOG = LoggerFactory.getLogger(ThreadDemo.class);

    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        LOG.info(" is flag {}", isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

}
