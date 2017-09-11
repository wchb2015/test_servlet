package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolTest.class);

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> fuList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(() -> {

                Thread.sleep(200);

                int sum = 0;
                for (int j = 0; j <= 100; j++) {
                    sum += j;
                }
                return sum;
            });

            fuList.add(future);
        }

        fuList.stream().forEach(f -> {
            try {
                LOG.info("result:{}", f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

/*        ThreadPoolDemo task = new ThreadPoolDemo();

        for (int i = 1; i <= 5; i++) {
            pool.submit(task);
        }
//        pool.submit(task);*/


        pool.shutdown();
    }
}


class ThreadPoolDemo implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolDemo.class);

    private AtomicInteger i = new AtomicInteger(0);

    @Override

    public void run() {

        while (i.get() <= 10) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOG.info(" running ,i :{}", i.getAndIncrement());
        }

        LOG.info("end , i:{}", i);
    }
}