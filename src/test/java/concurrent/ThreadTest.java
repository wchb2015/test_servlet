package concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadTest.class);

    int number = 10;

    public void firstMethod() throws Exception {
        synchronized (this) {
            number += 100;
            LOG.info("number:{}", number);
        }
    }

    public void secondMethod() throws Exception {
        synchronized (this) {
            /**
             * (休息2S,阻塞线程)
             * 以验证当前线程对象的机锁被占用时,
             * 是否被可以访问其他同步代码块
             */
//            Thread.sleep(2000);
            this.wait(2000);
            number += 100;
            LOG.info("number:{}", number);
        }
    }

    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();


        LOG.info("number final , {}", threadTest.number);
    }
}

/*
this.wait(20000);
"main" #1 prio=5 os_prio=31 tid=0x00007f858d000000 nid=0xe03 in Object.wait() [0x000070000ce41000]
        java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000007961b7188> (a concurrent.ThreadTest)
        at concurrent.ThreadTest.secondMethod(ThreadTest.java:28)
        - locked <0x00000007961b7188> (a concurrent.ThreadTest)
        at concurrent.ThreadTest.main(ThreadTest.java:47)

Thread.sleep(20000);
"main" #1 prio=5 os_prio=31 tid=0x00007ff096024000 nid=0xe03 waiting on condition [0x000070000bbf0000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at concurrent.ThreadTest.secondMethod(ThreadTest.java:27)
        - locked <0x00000007961b7140> (a concurrent.ThreadTest)
        at concurrent.ThreadTest.main(ThreadTest.java:47)
*/
