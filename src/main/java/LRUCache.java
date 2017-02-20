import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wenj91 on 2017-02-06.
 */
public class LRUCache<K, V> {

    private int capacity; //equal 0 -> no capacity limit; great then 0 -> capacity limit
    private final Map<K, V> cache;

    public LRUCache(int capacity){
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity < 0");
        }
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity==0?16:capacity);
    }

    public LRUCache add(K k, V v){
        synchronized (cache){
            if(cache.get(k)!=null){
                K lastKey = getLastKey();
                if(lastKey != null && !lastKey.equals(k)){
                    cache.remove(k);
                }
            }
            cache.put(k, v);

            if(capacity != 0 && cache.size()>capacity){
                K firstKey = getFirstKey();
                if(firstKey != null)
                    cache.remove(firstKey);
            }
        }

        return this;
    }

    public V get(K k){

        V v = cache.get(k);

        synchronized (cache) {
            if (v != null) {
                cache.remove(k);
                cache.put(k, v);
            }
        }
        return v;
    }

    public void del(K k){
        synchronized (cache){
            cache.remove(k);
        }
    }

    public Set<K> keys(){
        return cache.keySet();
    }

    public void evictAll(){
        synchronized (cache){
            cache.clear();
        }
    }

    public int getCacheSize(){
        return cache.size();
    }

    private K getFirstKey(){
        synchronized (cache) {
            Iterator<K> it = cache.keySet().iterator();
            K firstKey = null;
            if(it.hasNext())
                firstKey = it.next();

            return firstKey;
        }
    }

    private K getLastKey(){
        synchronized (cache) {
            Iterator<K> it = cache.keySet().iterator();
            K lastKey = null;
            while (it.hasNext())
                lastKey = it.next();

            return lastKey;
        }
    }

}
