package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class Pool {

    private static final Logger LOG = LoggerFactory.getLogger(Pool.class);

    private static final int MAX_AVAILABLE = 5;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x)) {
            available.release();

            LOG.info("putItem success , {}", x.hashCode());
        }
    }

    // Not a particularly efficient data structure; just for demo

    protected Object[] items = new Object[MAX_AVAILABLE];
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;

                LOG.info("getNextAvailableItem success!!! ");
                return items[i];
            }

        }
        LOG.error("getNextAvailableItem fail!!!");
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
