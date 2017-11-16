package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourcesRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class List extends ApiRequest {

    private DescribeResourcesRequest request;
    /**
     * 构造查询资源列表命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public List(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourcesRequest) super.request;
    }
    /**
     * 设置资源栈名,查询指定stack的资源列表
     * @param name 资源栈名
     * @return 自身引用
     */
    public List stackName(String name) {
        request.setStackName(name);
        return this;
    }
    /**
     * 设置资源栈id,查询指定stack的资源列表
     * @param stackId 资源栈id
     * @return 自身引用
     */
    public List stackId(String stackId) {
        request.setStackId(stackId);
        return this;
    }
}
