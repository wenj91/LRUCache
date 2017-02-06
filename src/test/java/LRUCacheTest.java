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
}
