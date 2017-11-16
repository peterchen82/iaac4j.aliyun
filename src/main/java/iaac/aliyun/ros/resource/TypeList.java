package iaac.aliyun.ros.resource;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeResourceTypesRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class TypeList extends ApiRequest {

    private DescribeResourceTypesRequest request;

    public TypeList(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourceTypesRequest) super.request;
    }

    public TypeList status(String name) {
        request.setSupportStatus(name);
        return this;
    }

}
