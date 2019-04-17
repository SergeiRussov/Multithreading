package resourcePool;

import entity.MyObject;
import util.Util;

import java.util.Random;

public class MyThreads extends Thread {

    private ResourcePool pool = ResourcePool.getInstance();
    private Random random = new Random();

    public void startWork() {

        for (int i = 0; i < 20; i++) {
            new MyThreads().start();
            i++;
        }
    }

    @Override
    public void run() {

        MyObject object = pool.getItem(random.nextInt(pool.size()));
        synchronized (object) {
            object.setUse(true);
            System.out.println(getName() + " объект получен " + object.getId());
            Util.sleep();
            object.setUse(false);
            System.out.println(getName() + " работа над объектом " + object.getId() + " окончена");
        }
    }
}
