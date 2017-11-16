package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceTypesRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 资源类型列表命令
 * Created by jiechen on 2017/11/1.
 */
public class TypeList extends ApiRequest {

    private DescribeResourceTypesRequest request;
    /**
     * 构造查询资源类型列表命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public TypeList(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceTypesRequest) super.request;
    }

    /**
     * 设置资源状态,查询指定状态的资源类型
     * @param name 状态名
     * @return
     */
    public TypeList status(String name) {
        request.setSupportStatus(name);
        return this;
    }

}
