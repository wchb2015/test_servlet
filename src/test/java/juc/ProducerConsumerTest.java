package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 演示一下等待唤醒机制.
 */
public class ProducerConsumerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerConsumerTest.class);

    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(producer, "生产者1").start();
        new Thread(consumer, "消费者1").start();

        LOG.info(" end , 剩余库存:{}", clerk.getProduct());

    }
}


class Clerk {

    private static final Logger LOG = LoggerFactory.getLogger(Clerk.class);

    private int product = 0;

    public int getProduct() {
        return product;
    }

    //进货
    public synchronized void get() {
        while (product > 1) {
            LOG.info("产品已经满了!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LOG.info("进货成功,现在的库存:{}", ++product);
        this.notifyAll();

    }

    //卖货
    public synchronized void sale() {
        while (product <= 0) {
            LOG.info("缺货!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        LOG.info("售货成功,现在的库存:{}", --product);
        this.notifyAll();
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

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
