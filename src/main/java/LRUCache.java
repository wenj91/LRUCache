import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wenj91 on 2017-02-06.
 */
public class LRUCache<T> {

    private int capacity; //equal 0 -> no capacity limit; great then 0 -> capacity limit
    private final Map<String, T> cache;

    public LRUCache(int capacity){
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity < 0");
        }
        this.capacity = capacity;
        this.cache = new LinkedHashMap<String, T>(capacity==0?16:capacity);
    }

    public LRUCache add(String key, T t){
        synchronized (cache){
            if(cache.get(key)!=null){
                cache.remove(key);
            }

            cache.put(key, t);

            if(capacity != 0 && cache.size()>capacity){
                String firstKey = getFirstKey();
                if(firstKey != null)
                    cache.remove(firstKey);
            }
        }

        return this;
    }

    public T get(String key){

        T t = cache.get(key);

        synchronized (cache) {
            if (t != null) {
                cache.remove(key);
                cache.put(key, t);
            }
        }
        return t;
    }

    public void del(String key){
        synchronized (cache){
            cache.remove(key);
        }
    }

    public int getCacheSize(){
        return cache.size();
    }

    private String getFirstKey(){
        synchronized (cache) {
            Iterator<String> it = cache.keySet().iterator();
            String firstKey = null;
            if(it.hasNext())
                firstKey = it.next();

            return firstKey;
        }
    }

}
