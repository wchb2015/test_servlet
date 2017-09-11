package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 题目:判断打印的 "one" or "two" ?
 */
public class Thread8MonitorTest {
    private static final Logger LOG = LoggerFactory.getLogger(Thread8MonitorTest.class);

    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();

        new Thread(() -> {
            number.getOne();
        }).start();

        new Thread(() -> {
            number2.getTwo();
        }).start();

        new Thread(() -> {
            number.getThree();
        }).start();
    }
}


class Number {
    private static final Logger LOG = LoggerFactory.getLogger(Number.class);

    public synchronized void getOne() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("one");
    }

    public synchronized void getTwo() {
        LOG.info("two");
    }

    public void getThree() {
        LOG.info("three");
    }
}