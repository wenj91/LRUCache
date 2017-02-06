import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wenj91 on 2017-02-06.
 */
public class LRUCacheTest {

    @Test
    public void capacityTest(){
        LRUCache<Integer> lruc = new LRUCache<>(0);
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
        new LRUCache<Integer>(-1);
    }

    @Test
    public void leftElementTest(){
        LRUCache<Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);
        Assert.assertTrue(lruc.get("1")==null);
    }

    @Test
    public void evictAllTest(){
        LRUCache<Integer> lruc = new LRUCache<>(3);
        lruc.add("1", 1);
        lruc.add("2", 2);
        lruc.add("3", 3);
        lruc.add("4", 4);

        lruc.evictAll();

        Assert.assertTrue(lruc.getCacheSize()==0);
    }
}
