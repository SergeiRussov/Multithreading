package deadlock;

import util.Util;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {

    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    private DeadlockExample1 deadLockExample1 = new DeadlockExample1();
    private DeadlockExample2 deadlockExample2 = new DeadlockExample2();

    public void startDeadlock() {

        deadLockExample1.start();
        deadlockExample2.start();
    }

    class DeadlockExample1 extends Thread {

        ReentrantLock locker = new ReentrantLock();

        @Override
        public void run() {

            setName("lockBypass1");

            locker.lock();

            if (locker.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " объект locker заблакирован");

                    Util.sleep();

                    if (deadlockExample2.locker.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " объект deadlockExample2.locker заблакирован");
                        } finally {
                            deadlockExample2.locker.unlock();
                            System.out.println(Thread.currentThread().getName() + " объект deadlockExample2.locker разблакирован");
                        }
                    }
                } finally {
                    locker.unlock();
                    System.out.println(Thread.currentThread().getName() + " locker разаблакирован");
                }
            }
        }
    }

    class DeadlockExample2 extends Thread {

        ReentrantLock locker = new ReentrantLock();

        @Override
        public void run() {

            setName("lockBypass2");

            locker.lock();

            if (locker.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " объект locker заблакирован");

                    Util.sleep();

                    if (deadLockExample1.locker.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " объект deadlockExample1.locker заблакирован");
                        } finally {
                            deadLockExample1.locker.unlock();
                            System.out.println(Thread.currentThread().getName() + " объект deadlockExample1.locker разблакирован");
                        }
                    }
                } finally {
                    locker.unlock();
                    System.out.println(Thread.currentThread().getName() + " locker разаблакирован");
                }
            }
        }
    }
}
