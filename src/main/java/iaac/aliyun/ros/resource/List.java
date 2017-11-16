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

    public List(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeResourcesRequest) super.request;
    }

    public List stackName(String name) {
        request.setStackName(name);
        return this;
    }

    public List stackId(String stackId) {
        request.setStackId(stackId);
        return this;
    }
}
