package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceDetailRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 资源详情命令
 * Created by jiechen on 2017/11/1.
 */
public class Describe extends ApiRequest {

    private DescribeResourceDetailRequest request;
    /**
     * 构造查询资源详情命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Describe(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceDetailRequest) super.request;
    }
    /**
     * 设置资源栈名,查询指定stack的资源
     * @param name 资源栈名
     * @return 自身引用
     */
    public Describe stackName(String name) {
        request.setStackName(name);
        return this;
    }
    /**
     * 设置资源栈id,查询指定stack的资源
     * @param stackId 资源栈id
     * @return 自身引用
     */
    public Describe stackId(String stackId) {
        request.setStackId(stackId);
        return this;
    }
    /**
     * 设置资源名,查询指定的资源名
     * @param resourceName 资源名
     * @return 自身引用
     */
    public Describe name(String resourceName) {
        request.setResourceName(resourceName);
        return this;
    }
}
