package iaac.aliyun.ros.event;

import com.aliyuncs.ros.model.v20150901.DescribeEventsRequest;
import iaac.aliyun.BaseApi;
import iaac.aliyun.credential.ApiCredential;

/**
 * 资源编排模板事件相关api
 * Created by jiechen on 2017/11/2.
 */
public class Event extends BaseApi {
    public class Status {
        public static final String COMPLETE = "COMPLETE";
        public static final String FAILED = "FAILED";
        public static final String IN_PROGRESS = "IN_PROGRESS";
        public static final String DELETE_FAILED = "DELETE_FAILED";
    }
    /**
     * 构造一个api命令对象
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Event(String region, ApiCredential credential) {
        super(region, credential);
    }

    /**
     * 获取资源栈的事件列表
     * @return
     */
    public List list() {
        return new List(new DescribeEventsRequest(), this.region, this.credential);
    }

}
