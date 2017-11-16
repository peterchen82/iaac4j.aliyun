package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceTypeDetailRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class TypeDescribe extends ApiRequest {

    private DescribeResourceTypeDetailRequest request;

    public TypeDescribe(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceTypeDetailRequest) super.request;
    }

    public TypeDescribe type(String name) {
        request.setTypeName(name);
        return this;
    }

}
