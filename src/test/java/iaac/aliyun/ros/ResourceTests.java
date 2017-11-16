package iaac.aliyun.ros;

import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.Regions;
import iaac.aliyun.ros.stack.Stack;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ResourceTests {

    //API凭据,请根据实际情况设置
    private static final String ACCESS_KEY = "<YOUR ACCESS KEY>";
    private static final String ACCESS_SECRET = "<YOUR ACCESS SECRET>";

    //测试用的stack数据,请根据实际情况设置
    private static final String stackName = "spark";     //stack名字
    private static final String stackId = "测试资源编排id";  //stack Id


    private static final ApiCredential credential = new ApiCredential(ACCESS_KEY, ACCESS_SECRET);
    private static final Stack stack = new Stack(Regions.CN_HANGZHOU, credential);



    public ResourceTests() throws Exception {

    }

    @Test
    public void testList() throws Exception {
        Map<String, Object> response = stack.resource().list()
                .stackName(stackName).stackId(stackId)
                .run();
        List<Map> resources = (List<Map>) response.get("List");
        for (Map resource : resources) {
            System.out.println(resource.get("PhysicalId") + ":" + resource.get("Name") + "(" + resource.get("Type") + ")");
        }
    }

    @Test
    public void testDescribe() throws Exception {
        Map<String, Object> response = stack.resource().describe()
                .stackName(stackName).stackId(stackId).name("Master")
                .run();
        System.out.println(response);
    }

    @Test
    public void testTypeList() throws Exception {
        Map<String, Object> response = stack.resource().typeList().run();
        List<String> resources = (List<String>) response.get("ResourceTypes");
        for (String resource : resources) {
            System.out.println(resource);
        }
    }

    @Test
    public void testTypeDescribe() throws Exception {
        Map<String, Object> response = stack.resource().typeDescribe().type("ALIYUN::ECS::Instance").run();
        System.out.println(response);
    }

    @Test
    public void testTypeTemplate() throws Exception {
        Map<String, Object> response = stack.resource().typeTemplate().type("ALIYUN::ECS::Instance").run();
        System.out.println(response);
    }

}
