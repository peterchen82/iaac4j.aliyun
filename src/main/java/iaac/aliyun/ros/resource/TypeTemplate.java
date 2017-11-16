package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceTypeTemplateRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class TypeTemplate extends ApiRequest {

    private DescribeResourceTypeTemplateRequest request;

    public TypeTemplate(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceTypeTemplateRequest) super.request;
    }

    public TypeTemplate type(String name) {
        request.setTypeName(name);
        return this;
    }

}
