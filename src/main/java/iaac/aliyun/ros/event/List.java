package iaac.aliyun.ros.event;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeEventsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 获取Stack的事件列表
 * Created by jiechen on 2017/11/1.
 */
public class List extends ApiRequest {

    private DescribeEventsRequest request;
    /**
     * 构造查询事件列表命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public List(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeEventsRequest) super.request;
    }

    /**
     * 设置资源栈名
     * @param name 资源栈名
     * @return 自身引用
     */
    public List stackName(String name) {
        request.setStackName(name);
        return this;
    }
    /**
     * 设置资源栈id
     * @param stackId 资源栈id
     * @return 自身引用
     */
    public List stackId(String stackId) {
        request.setStackId(stackId);
        return this;
    }
    /**
     * 设置资源名,可过滤出只属于这个资源的事件
     * @param name 资源名
     * @return 自身引用
     */
    public List resourceName(String name) {
        request.setResourceName(name);
        return this;
    }
    /**
     * 设置事件状态,可过滤出只属于这个状态的事件
     * @param status 状态名
     * @return 自身引用
     */
    public List status(String status) {
        request.setResourceStatus(status);
        return this;
    }
    /**
     * 设置事件类型,可过滤出只属于这个类型的事件
     * @param type 事件类型名
     * @return 自身引用
     */
    public List type(String type) {
        request.setResourceType(type);
        return this;
    }
    /**
     * 设置分页数
     * @param pageNumber 分页数
     * @return 自身引用
     */
    public List page(int pageNumber) {
        request.setPageNumber(pageNumber);
        return this;
    }
    /**
     * 设置每次请求返回的条数
     * @param pageSize 返回的条数
     * @return 自身引用
     */
    public List size(int pageSize) {
        request.setPageSize(pageSize);
        return this;
    }
}
