package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtomicTest {

    private static final Logger LOG = LoggerFactory.getLogger(AtomicTest.class);

    public static void main(String[] args) {

        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }

        LOG.info("end");
    }
}


class AtomicDemo implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(AtomicDemo.class);

    private int serialNumber = 0;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info(" serialNumber :{}", getSerialNumber());

    }

    public int getSerialNumber() {
        return serialNumber++;
    }

}