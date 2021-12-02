package io.zipcoder;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {
    private ReentrantLock lock;

    public SafeCopier(String toCopy) {
        super(toCopy);
        this.lock = new ReentrantLock();
    }

    public void run() {

        while(this.stringIterator.hasNext()) {
            this.lock.lock();
            try {
                if (this.stringIterator.hasNext()) {
                    this.copied += (this.stringIterator.next() + " ");
                }
            } finally {
                this.lock.unlock();
            }
        }
    }
}
