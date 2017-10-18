package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 题目:判断打印的 "one" or "two" ?
 */
public class Thread8MonitorTest {
    private static final Logger LOG = LoggerFactory.getLogger(Thread8MonitorTest.class);

    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();

        new Thread(() -> {
            number1.getTwo();
        }, "TWO___###").start();

        new Thread(() -> {
            number1.getOne();
        }, "ONE___###").start();

        new Thread(() -> {
            number1.getThree();
        }, "THREE1___###").start();

        new Thread(() -> {
            number1.getThree();
        }, "THREE2___###").start();

        new Thread(() -> {
            number1.getThree();
        }, "THREE3___###").start();
    }
}

class Number {
    private static final Logger LOG = LoggerFactory.getLogger(Number.class);

    public synchronized void getOne() {
        LOG.info("one");
    }

    public synchronized void getTwo() {
        try {
            LOG.info("start sleep!~");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("two");
    }

    public void getThree() {
        LOG.info("three");
    }
}