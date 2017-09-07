package concurrent;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    @Test
    public void test() throws InterruptedException {
        Pool pool = new Pool();

        pool.putItem(new Object());
        pool.putItem(new Object());
        pool.putItem(new Object());
        pool.putItem(new Object());
        pool.putItem(new Object());

        pool.getItem();
        pool.getItem();
        pool.getItem();
        pool.getItem();
        pool.getItem();

    }

    @Test
    public void testBoundedBuffer() throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer(40);

        boundedBuffer.isEmpty();
        boundedBuffer.isFull();

        Object o = new Object();
        LOG.info("o:{}", o.hashCode());

        boundedBuffer.put(new Object());
        Object o1 = boundedBuffer.take();
        Object o2 = boundedBuffer.take();
        LOG.info("o1:{}", o1.hashCode());
        LOG.info("o2:{}", o2.hashCode());
    }

    @Test
    public void testBoundedBufferFromBook() {

        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Assert.isTrue(bb.isEmpty());
        org.junit.Assert.assertEquals(false, bb.isFull());
    }

    @Test
    public void testBoundedBufferFromBook2() {

        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Assert.isTrue(bb.isEmpty());
        org.junit.Assert.assertEquals(false, bb.isFull());
    }
}
