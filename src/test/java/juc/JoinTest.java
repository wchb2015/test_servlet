package juc;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinTest {

    private static final Logger LOG = LoggerFactory.getLogger(JoinTest.class);

    @Test
    public void test() throws InterruptedException {
        LOG.info("start.");
        AThread at = new AThread();
        BThread bt = new BThread();

        at.start();
        bt.start();
        at.join();//Waits for this thread to die.
        LOG.info(" end.");

    }
}

class BThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(BThread.class);

    public BThread() {
        super("B线程");
    }

    public void run() {
        LOG.info("start.");
        try {
            for (int i = 0; i < 5; i++) {
                LOG.info(" loop at, i:{}", i);
                Thread.sleep(1000);
            }
            LOG.info(" end.");
        } catch (Exception e) {
            LOG.error("Exception ", e);
        }
    }
}

class AThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(AThread.class);

    public AThread() {
        super("A线程");
    }

    public void run() {
        LOG.info("start.");
        try {
            Thread.sleep(3000);
            LOG.info(" end.");
        } catch (Exception e) {
            LOG.error("Exception ", e);
        }
    }
}
