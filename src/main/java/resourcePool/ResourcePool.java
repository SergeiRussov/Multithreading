package resourcePool;

import entity.MyObject;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool {

    private static ResourcePool _instance = null;
    private volatile List<MyObject> pool = new ArrayList<MyObject>();

    private ResourcePool() {

        pool.add(new MyObject(1, false));
        pool.add(new MyObject(2 ,false));
        pool.add(new MyObject(3 ,false));
        pool.add(new MyObject(4 ,false));
        pool.add(new MyObject(5 ,false));
        pool.add(new MyObject(6 ,false));
        pool.add(new MyObject(7 ,false));
        pool.add(new MyObject(8 ,false));
        pool.add(new MyObject(9 ,false));
        pool.add(new MyObject(10 ,false));
    }

    public static synchronized ResourcePool getInstance() {

        if (_instance == null) {
            _instance = new ResourcePool();
        }
        return _instance;
    }

    public synchronized MyObject getItem(int i) {

        return pool.get(i);
    }

    public synchronized int size() {

        return pool.size();
    }
}
