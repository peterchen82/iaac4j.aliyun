package iaac.aliyun.ros.template;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.DescribeTemplateRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Describe extends ApiRequest {

    private DescribeTemplateRequest request;

    public Describe(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeTemplateRequest) super.request;
    }

    public Describe stackName(String name) {
        request.setStackName(name);
        return this;
    }

    public Describe stackId(String stackId) {
        request.setStackId(stackId);
        return this;
    }
}
