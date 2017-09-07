package concurrent;

import java.util.concurrent.Semaphore;

/**
 * 摘自Java并发编程实战
 *
 * @param <E>
 */
public class BoundedBuffer<E> {

    private final Semaphore aviailableItems, availableSpaces;

    private final E[] items;

    private int putPosition = 0, takePosition = 0;


    public BoundedBuffer(int capacity) {

        aviailableItems = new Semaphore(0);

        availableSpaces = new Semaphore(capacity);

        items = (E[]) new Object[capacity];

    }

    public boolean isEmpty() {
        return aviailableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {

        availableSpaces.acquire();

        doInsert(x);

        aviailableItems.release();
    }

    public E take() throws InterruptedException {
        aviailableItems.acquire();

        E item = doExtract();

        availableSpaces.release();

        return item;
    }

    private synchronized void doInsert(E x) {
        int i = putPosition;

        items[i] = x;

        putPosition = (++i == items.length) ? 0 : i;
    }

    private synchronized E doExtract() {
        int i = takePosition;

        E x = items[i];

        items[i] = null;

        takePosition = (++i == items.length) ? 0 : i;

        return x;
    }

}
