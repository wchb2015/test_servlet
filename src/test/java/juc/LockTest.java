package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
        new Thread(ticket, "4号窗口").start();
        new Thread(ticket, "5号窗口").start();
    }

}


class Ticket implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Ticket.class);

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        while (tick > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                LOG.info("完成售票,余票tick:{}", --tick);
            } finally {
                lock.unlock();
            }
        }
    }
}
