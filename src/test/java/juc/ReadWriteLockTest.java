package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * "写写","读写" 需要互斥
 */
public class ReadWriteLockTest {
    private static final Logger LOG = LoggerFactory.getLogger(ReadWriteLockTest.class);


    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();

        new Thread(() -> {
            rw.set((int) (Math.random() * 100));
        }, "写线程1").start();

        new Thread(() -> {
            rw.set((int) (Math.random() * 100));
        }, "写线程2").start();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                rw.get();
            },"读线程"+i).start();
        }
    }
}


class ReadWriteLockDemo {

    private static final Logger LOG = LoggerFactory.getLogger(ReadWriteLockDemo.class);

    private int number = 9;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {

        lock.readLock().lock();

        try {
            LOG.info("number:{}", number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int number) {
        lock.writeLock().lock();
        try {
            LOG.info("set number:{}", number);
            this.number = number;
        } finally {
            lock.writeLock().unlock();
        }
    }
}