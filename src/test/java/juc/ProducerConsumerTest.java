package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerConsumerTest.class);
    
    public static void main(String[] args) {

    }
}


class Clerk {

    private static final Logger LOG = LoggerFactory.getLogger(Clerk.class);

    private int product = 0;

    //进货
    public synchronized void get() {
        if (product > 10) {
            LOG.info("产品已经满了!");
        } else {
            LOG.info("进货成功,现在的库存:{}", ++product);
        }
    }

    //卖货
    public synchronized void sale() {
        if (product <= 0) {
            LOG.info("缺货!");
        } else {
            LOG.info("售货成功,现在的库存:{}", --product);
        }
    }
}


//生产者
class Producer implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);


    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {
            clerk.get();
        }

    }
}

//消费者
class Consumer implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
