package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CASTest {

    private static final Logger LOG = LoggerFactory.getLogger(CASTest.class);

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectedValue = cas.get();
                int newValue = (int) (Math.random() * 100);
                LOG.info("expectedValue:{},newValue:{}", expectedValue, newValue);
                LOG.info("isSuccess:{},newValue:{}", cas.compareAndSet(expectedValue, newValue), cas.get());
            }).start();
        }

        LOG.info("end");
    }

}

class CompareAndSwap {

    private Logger LOG = LoggerFactory.getLogger(CompareAndSwap.class);
    private int value;

    //获取内存值
    public synchronized int get() {
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;

        if (value == expectedValue) {
            this.value = newValue;
        }

        LOG.info("oldValue:{}", oldValue);
        return oldValue;
    }

    //设值
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }

}