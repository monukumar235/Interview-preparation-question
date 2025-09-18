package singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
    public static  class Cache{
        public static volatile Cache cache;
        private Cache(){
            if(cache!=null){
                throw new RuntimeException("Dont be too smart");
            }
        }
        public static Cache getInstance(){
            if(cache==null){
                synchronized (Cache.class){
                    if(cache==null){
                        cache=new Cache();
                    }
                }
            }
            return cache;
        }
        ConcurrentHashMap<String,Object> cacheMap = new ConcurrentHashMap<>();

        public void put(String key,Object val){
            cacheMap.put(key,val);
        }
        public Object get(String key){
            return cacheMap.get(key);
        }
        public void remove(String key){
            cacheMap.remove(key);
        }

        public void clear(){
            cacheMap.clear();
        }
    }
    public static void main(String[] args) {
        Cache cache = Cache.getInstance();

        cache.put("monu","ranchi");
        System.out.println(cache.get("monu"));
        cache.remove("monu");
        System.out.println(cache.get("monu"));
    }
}
