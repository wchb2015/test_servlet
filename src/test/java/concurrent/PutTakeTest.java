package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PutTakeTest {

    private static final Logger LOG = LoggerFactory.getLogger(PutTakeTest.class);

    private static final ExecutorService pool = Executors.newCachedThreadPool();

    private final AtomicInteger putSum = new AtomicInteger(0);

    private final AtomicInteger takeSum = new AtomicInteger(0);

    private final CyclicBarrier barrier;

    private final int nTrials, nParis;

    private final BoundedBuffer<Integer> bb;

    PutTakeTest(int capacity, int npairs, int ntrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nParis = npairs;
        this.nTrials = ntrials;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nParis; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await();//等待所有的线程就绪
            barrier.await();//等待所有的线程执行完成

            org.junit.Assert.assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new PutTakeTest(10, 10, 10000).test();//示例参数
        pool.shutdown();
    }

    class Producer implements Runnable {

        @Override
        public void run() {

            int seed = (this.hashCode() ^ (int) System.nanoTime());
            int sum = 0;

            try {
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {

            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int xorShift(int seed) {
        return seed;
    }
}

