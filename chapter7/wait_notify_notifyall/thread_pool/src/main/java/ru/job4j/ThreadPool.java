package ru.job4j;

import java.util.LinkedList;

/**
 * Thread pool.
 *
 * @author Alexey Voronin.
 * @since 01.05.2017.
 */
public class ThreadPool {

    /**
     * Number of pool.
     */
    private final int threadsCount;

    /**
     * Threads array.
     */
    private final Thread[] pool;

    /**
     * Task queue.
     */
    private final LinkedList<Runnable> queue;

    /**
     * Constructor.
     */
    public ThreadPool() {
        this.threadsCount = Runtime.getRuntime().availableProcessors();
        this.queue = new LinkedList<>();
        this.pool = new Thread[this.threadsCount];
        for (int i = 0; i < this.threadsCount; i++) {
            pool[i] = new Thread(new Worker());
            pool[i].start();
        }
    }

    /**
     * Add new task to the queue.
     *
     * @param runnable task.
     */
    public void addTask(final Runnable runnable) {
        synchronized (this.queue) {
            this.queue.add(runnable);
            this.queue.notifyAll();
        }
    }

    /**
     * Class Worker performs the task.
     */
    private class Worker implements Runnable {
        @Override
        public void run() {
            Runnable runnable;
            while (true) {
                synchronized (ThreadPool.this.queue) {
                    while (ThreadPool.this.queue.isEmpty()) {
                        try {
                            ThreadPool.this.queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    runnable = ThreadPool.this.queue.removeFirst();
                }
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
