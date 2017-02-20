import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by wenj91 on 2017-02-06.
 */
public class LRUCacheTest {

    @Test
    public void addTest(){
        LRUCache<String, Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("3", 4);
        Assert.assertTrue(lruc.get("3")==4);
    }

    @Test
    public void capacityTest(){
        LRUCache<String, Integer> lruc = new LRUCache<>(0);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        Assert.assertTrue(lruc.getCacheSize()==3);

        lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);
        Assert.assertTrue(lruc.getCacheSize()==3);

        lruc = new LRUCache<>(0);
        for(int i=0; i<20; i++){
            lruc.add(String.valueOf(i), i);
        }
        Assert.assertTrue(lruc.getCacheSize()==20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalCapacityTest(){
        new LRUCache<String, Integer>(-1);
    }

    @Test
    public void leftElementTest(){
        LRUCache<String, Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);
        Assert.assertTrue(lruc.get("1")==null);
    }

    @Test
    public void evictAllTest(){
        LRUCache<String, Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);

        lruc.evictAll();

        Assert.assertTrue(lruc.getCacheSize()==0);
    }

    @Test
    public void keysTest(){
        LRUCache<String, Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);

        Set<String> keys = lruc.keys();
        Assert.assertFalse(keys.contains("1"));
        Assert.assertTrue(keys.contains("2"));
        Assert.assertTrue(keys.contains("3"));
        Assert.assertTrue(keys.contains("4"));
    }
}
