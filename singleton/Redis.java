package singleton;

import java.util.concurrent.ConcurrentHashMap;

public class Redis {
    public static class Cache{
        public static volatile Cache instances;
        ConcurrentHashMap<String,Object> cache = new ConcurrentHashMap<>();
        private Cache(){};

        public static Cache getInstance(){
            if(instances ==null){
                synchronized (Cache.class){
                    if(instances ==null)
                    {
                        instances = new Cache();
                    }
                }
            }
            return instances;
        }
        public void put(String key,Object value)
        {
            cache.put(key,value);
        }
        public Object get(String key) {
            return cache.get(key);
        }
        public boolean isEmpty(){
            return cache.isEmpty();
        }
        public boolean containsKey(String key){
            return cache.containsKey(key);
        }
        public void remove(String key){
            cache.remove(key);
        }
    }
    public static void main(String[] args) {
        Cache cache = Cache.getInstance();
        cache.put("1",1);
        cache.put("1",2);
        System.out.println(cache.get("1"));
    }
}
