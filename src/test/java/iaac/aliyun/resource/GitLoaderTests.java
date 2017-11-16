package iaac.aliyun.resource;

import iaac.aliyun.credential.UsernameCredential;
import org.junit.Assert;
import org.junit.Test;

public class GitLoaderTests {

    @Test
    public void testLoad() throws Exception {
        Loader loader = new GitLoader();
        UsernameCredential credential = new UsernameCredential("youname", "password");
        loader.credential(credential).location("http://yousite.com/youname/project.git", "spark/spark-params.json");
        String content = loader.load();
        System.out.println(content);
        Assert.assertNotEquals(0, content.length());
    }

}
