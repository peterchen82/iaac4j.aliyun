package iaac.aliyun.ros;

import iaac.aliyun.Regions;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.ros.stack.Stack;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StackTests {
    //API凭据,请根据实际情况设置
    private static final String ACCESS_KEY = "<YOUR ACCESS KEY>";
    private static final String ACCESS_SECRET = "<YOUR ACCESS SECRET>";

    //测试用的stack数据,请根据实际情况设置
    private static final String stackName = "spark";     //stack名字
    private static final String stackId = "测试资源编排id";  //stack Id
    private static final String ecsPassword = "YourPassword19821019";  //stack中ecs的口令

    private static final String rosTpl = "iaac/aliyun/spark-ros.json";  //资源编排模板文件位置
    private static final String rosTplUrl = "http://yousite.com/paramsFile";  //资源编排模板url
    private static final String rosGitUrl = "http:/yousite.com/youname/project.git";  //资源编排模板git地址

    private static final String paramsFile = "iaac/aliyun/spark-params.json";  //参数模板文件位置
    private static final String paramsUrl = "http://yousite.com/paramsFile";   //参数模板url
    private static final String paramsGitUrl = "http:/yousite.com/youname/project.git";  //参数模板git地址


    //定义鉴权信息
    private static final ApiCredential credential = new ApiCredential(ACCESS_KEY, ACCESS_SECRET);
    //创建资源栈对象
    private static final Stack stack = new Stack(Regions.CN_HANGZHOU, credential);

    public StackTests() throws Exception {

    }
    @Test
    public void testRosPreviewByFile() throws Exception {
        Map<String, Object> response = stack.preview()
                .name(stackName)
                .loadTemplate(stack.fileLoader().location(rosTpl))
                .loadParameters(stack.fileLoader().location(paramsFile))
                .timeoutMins(30).run();
        System.out.println(response);
    }
    @Test
    public void testRosPreviewByUrl() throws Exception {
        Map<String, Object> response = stack.preview()
                .name(stackName)
                .loadTemplate(stack.httpLoader().location(rosTplUrl))
                .loadParameters(stack.httpLoader().location(paramsUrl))
                .timeoutMins(30).run();
        System.out.println(response);
    }
    @Test
    public void testRosPreviewByGit() throws Exception {
        Map<String, Object> response = stack.preview()
                .name(stackName)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .timeoutMins(30).run();
        System.out.println(response);
    }
    @Test
    public void testRosCreate() throws Exception {

        Map<String, Object> response = stack.create()
                .name(stackName)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .timeoutMins(30).run();
        if (response.get("Message") == null) {
            System.out.println("stack 创建成功");
            System.out.println("stack id:" + response.get("Id"));
            System.out.println("stack name:" + response.get("Name"));
        } else {
            System.out.println("stack 创建失败");
            System.out.println("message:" + response.get("Message"));
            System.out.println("code:" + response.get("Code"));
        }
    }

    @Test
    public void testRosDelete() throws Exception {
        stack.delete().name(stackName).id(stackId).run();
        System.out.println("stack 删除成功");
    }
    @Test
    public void testRosDeleteOnlyName() throws Exception {
        stack.delete().name(stackName).run();
        System.out.println("stack 删除成功");
    }
    @Test
    public void testRosAbandon() throws Exception {
        stack.abandon().name(stackName).id(stackId).run();
        System.out.println("stack 废除成功");
    }
    @Test
    public void testRosAbandonOnlyName() throws Exception {
        stack.abandon().name(stackName).run();
        System.out.println("stack 废除成功");
    }
    @Test
    public void testRosList() throws Exception {
        Map<String, Object> response = stack.list().run();
        List<Map> stacks = (List<Map>) response.get("Stacks");
        for (Map stack : stacks) {
            System.out.println(stack.get("Name") + "(" + stack.get("Id") + ")");
        }
    }
    @Test
    public void testRosFindOne() throws Exception {
        Map<String, Object> response = stack.findOne().name("spark").run();
        System.out.println(response);
    }
    @Test
    public void testRosDescribe() throws Exception {

        Map<String, Object> response = stack.describe().name(stackName).id(stackId).run();
        System.out.println(response);
    }
    @Test
    public void testRosDescribeOnlyName() throws Exception {

        Map<String, Object> response = stack.describe().name(stackName).run();
        System.out.println(response);
    }

    @Test
    public void testRosUpdate() throws Exception {

        Map<String, Object> response = stack.update()
                .name(stackName)
//                .id(stackId)
                .loadTemplate(stack.gitLoader().location(rosGitUrl,"spark/spark-ros.json"))
                .loadParameters(stack.gitLoader().location(paramsGitUrl,"spark/spark-params.json"))
                .parameters("NumOfNodes", 1)
                .timeoutMins(30)
                .run();
        System.out.println(response);
    }

    @Test
    public void testGetRegions() throws Exception {
        Map<String, Object> response = stack.regions().run();
        List<Map> regions = (List<Map>) response.get("Regions");
        for (Map region : regions) {
            System.out.println(region);
        }
    }
}
