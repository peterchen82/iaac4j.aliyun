package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.DescribeStackDetailRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Describe extends ApiRequest {
    protected DescribeStackDetailRequest request;

    private Stack stack;

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public Describe(DescribeStackDetailRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeStackDetailRequest) super.request;
    }

    public Describe name(String name) {
        request.setStackName(name);
        return this;
    }

    public Describe id(String stackId) {
        request.setStackId(stackId);
        return this;
    }
    public Map run() throws Exception {
        if (request.getStackId() == null || request.getStackId().equals("")) {
            Map stackInfo = stack.findOne().name(request.getStackName()).run();
            if (stackInfo != null) {
                request.setStackId((String) stackInfo.get("Id"));
            }else{
                return null;
            }
        }
        if (request.getStackId() == null || request.getStackId().equals("")) {
            return null;
        }
        if (request.getStackName() == null || request.getStackName().equals("")) {
            return null;
        }
        return super.run();
    }
}
