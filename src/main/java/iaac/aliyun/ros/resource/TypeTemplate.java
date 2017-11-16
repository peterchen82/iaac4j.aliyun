package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceTypeTemplateRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 查询资源对应模板详情的命令
 * Created by jiechen on 2017/11/1.
 */
public class TypeTemplate extends ApiRequest {

    private DescribeResourceTypeTemplateRequest request;
    /**
     * 构造查询资源类型模板对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public TypeTemplate(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceTypeTemplateRequest) super.request;
    }

    /**
     * 查询指定资源类型名的模板信息
     * @param name 资源类型名
     * @return 自身引用
     */
    public TypeTemplate type(String name) {
        request.setTypeName(name);
        return this;
    }

}
