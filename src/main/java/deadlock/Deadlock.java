package deadlock;

import util.Util;

public class Deadlock {

    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    public void startDeadlock() {

        DeadlockExample1 deadlockExample1 = new DeadlockExample1();
        DeadLockExample2 deadLockExample2 = new DeadLockExample2();

        deadlockExample1.start();
        deadLockExample2.start();
    }

    class DeadlockExample1 extends Thread {

        @Override
        public void run() {

            currentThread().setName("deadlockExample1");

            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + " захватил ресурс obj1");

                Util.sleep();

                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + " захватил ресурс obj2");
                }
            }
        }
    }

    class DeadLockExample2 extends Thread {

        @Override
        public void run() {

            currentThread().setName("deadlockExample2");

            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " захватил ресурс obj2");

                Util.sleep();

                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + " захватил ресурс obj1");
                }
            }
        }
    }
}
