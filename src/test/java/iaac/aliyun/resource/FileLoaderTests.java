package iaac.aliyun.resource;

import org.junit.Assert;
import org.junit.Test;

public class FileLoaderTests {

    @Test
    public void testLoad() throws Exception {
        Loader loader = new FileLoader();
        loader.location("iaac/aliyun/spark-ros.json");
        String content = loader.load();
        Assert.assertNotEquals(0,content.length());
    }

}
