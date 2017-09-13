import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test04() {
        int value = 11;
        int expectedValue = 11;
        int newValue = 13;

        int oldValue = value;

        if (value == expectedValue) {
            value = newValue;
        }

        LOG.info("oldValue:{}", oldValue);
        LOG.info("Value:{}", value);
    }

    @org.junit.Test
    public void test02() {
        int i = 10;
        for (; ; ) {
            LOG.info("i:{}", i);
            i++;
            if (i > 30) {
                return;
            }
        }
    }

    @org.junit.Test
    public void test01() {

        AtomicInteger atomicInteger = new AtomicInteger(10);
//        atomicInteger.getAndIncrement();
//        atomicInteger.incrementAndGet();
        LOG.info("atomicInteger:{}", atomicInteger.incrementAndGet());

    }

    @org.junit.Test
    public void test() {

        int i = 1;

        LOG.info("i:{}", i++);

    }


}
