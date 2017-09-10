package juc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    private static final Logger LOG = LoggerFactory.getLogger(CallableTest.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadDemo ctd = new CallableThreadDemo();


        List<String> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FutureTask<List<String>> tempFT = new FutureTask<>(ctd);
            new Thread(tempFT).start();

            result.addAll(tempFT.get());
        }


        LOG.info("result:{}", result);

    }
}


class CallableThreadDemo implements Callable<List<String>> {

    private static final Logger LOG = LoggerFactory.getLogger(CallableThreadDemo.class);


    @Override
    public List<String> call() throws Exception {
        LOG.info("running");

        return Arrays.asList("aa", "bb", "cc");
    }
}