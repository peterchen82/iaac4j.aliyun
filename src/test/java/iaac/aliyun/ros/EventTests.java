package iaac.aliyun.ros;

import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.Regions;
import iaac.aliyun.ros.event.Event;
import iaac.aliyun.ros.stack.Stack;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class EventTests {

    //API凭据,请根据实际情况设置
    private static final String ACCESS_KEY = "<YOUR ACCESS KEY>";
    private static final String ACCESS_SECRET = "<YOUR ACCESS SECRET>";

    //测试用的stack数据,请根据实际情况设置
    private static final String stackName = "spark";     //stack名字
    private static final String stackId = "测试资源编排id";  //stack Id


    private static final ApiCredential credential = new ApiCredential(ACCESS_KEY, ACCESS_SECRET);
    private static final Stack ros = new Stack(Regions.CN_HANGZHOU, credential);


    public EventTests() throws Exception {

    }

    @Test
    public void testList() throws Exception {
        Map<String, Object> response = ros.event().list()
                .stackName(stackName).stackId(stackId)
                .status(Event.Status.COMPLETE)
                .type("ALIYUN::ROS::Stack")
                .resourceName(stackName)
                .run();

        List<Map> events = (List<Map>) response.get("Events");
        for (Map event : events) {
            System.out.println(event.get("ResourceName") + ":" + event.get("ResourceType") + "(" + event.get("Status") + ")");
        }
    }

}
