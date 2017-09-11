package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替
 * <p>
 * 编写一个程序,开启3个线程,这三个线程的ID分别为"A","B","C",每个线程将自己的ID在屏幕上打印10遍
 * 要求:输出的结果必须按顺序显示
 * 如:ABCABCABC... 依次递归
 */
public class ABCAlterTest {

    private static final Logger LOG = LoggerFactory.getLogger(ABCAlterTest.class);

    public static void main(String[] args) {
        AlterDemo ad = new AlterDemo();

        new Thread(() -> {

            for (int i = 0; i <= 3; i++) {
                ad.loopA(i);
            }

        }, "A线程").start();

        new Thread(() -> {

            for (int i = 0; i <= 3; i++) {
                ad.loopB(i);
            }

        }, "B线程").start();

        new Thread(() -> {

            for (int i = 0; i <= 3; i++) {
                ad.loopC(i);
            }

        }, "C线程").start();
    }

}

class AlterDemo {

    private static final Logger LOG = LoggerFactory.getLogger(AlterDemo.class);

    private int number = 1;//当前正在执行线程的标记

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     * @param totalLoop 循环第几轮
     */
    public void loopA(int totalLoop) {

        lock.lock();
        try {

            //1.判断
            if (number != 1) {
                condition1.await();
            }

            //2.打印
            for (int i = 1; i <= 1; i++) {
                LOG.info("i:{},totalLoop:{}", i, totalLoop);
            }

            //3.唤醒
            number = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * @param totalLoop 循环第几轮
     */
    public void loopB(int totalLoop) {

        lock.lock();
        try {

            //1.判断
            if (number != 2) {
                condition2.await();
            }

            //2.打印
            for (int i = 1; i <= 1; i++) {
                LOG.info("i:{},totalLoop:{}", i, totalLoop);
            }

            //3.唤醒
            number = 3;
            condition3.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * @param totalLoop 循环第几轮
     */
    public void loopC(int totalLoop) {

        lock.lock();
        try {

            //1.判断
            if (number != 3) {
                condition3.await();
            }

            //2.打印
            for (int i = 1; i <= 1; i++) {
                LOG.info("i:{},totalLoop:{}", i, totalLoop);
            }

            //3.唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
//            LOG.info("---------");
        }

    }
}
