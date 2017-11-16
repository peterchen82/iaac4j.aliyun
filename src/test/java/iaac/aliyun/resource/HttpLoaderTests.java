package iaac.aliyun.resource;

import org.junit.Assert;
import org.junit.Test;

public class HttpLoaderTests {

    @Test
    public void testLoad() throws Exception {
        Loader loader = new HttpLoader();
        loader.location("http://yousite/ros.json");
        String content = loader.load();
        Assert.assertNotEquals(0,content.length());
    }

}
