package iaac.aliyun.ros;

import iaac.aliyun.Regions;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.ros.stack.Stack;
import org.junit.Test;

import java.util.Map;

public class TemplateTests {

    //API凭据,请根据实际情况设置
    private static final String ACCESS_KEY = "<YOUR ACCESS KEY>";
    private static final String ACCESS_SECRET = "<YOUR ACCESS SECRET>";

    //测试用的stack数据,请根据实际情况设置
    private static final String stackName = "spark";     //stack名字
    private static final String stackId = "测试资源编排id";  //stack Id
    private static final String rosTpl = "iaac/aliyun/spark-ros.json";  //资源编排模板文件位置


    private static final ApiCredential credential = new ApiCredential(ACCESS_KEY, ACCESS_SECRET);
    private static final Stack stack = new Stack(Regions.CN_HANGZHOU, credential);



    public TemplateTests() throws Exception {

    }
    @Test
    public void testValidate() throws Exception {
        Map<String, Object> response = stack.template().validate()
                .template(stack.fileLoader().location(rosTpl).load()).run();
        System.out.println(response);
    }

    @Test
    public void testDescribe() throws Exception {
        Map<String, Object> response = stack.template().describe()
                .stackName(stackName).stackId(stackId)
                .run();
        System.out.println(response);
    }

}
