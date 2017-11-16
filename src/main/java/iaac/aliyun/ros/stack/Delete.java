package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.DeleteStackRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Delete extends ApiRequest {
    protected DeleteStackRequest request;

    private Stack stack;

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public Delete(DeleteStackRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DeleteStackRequest) super.request;
    }

    public Delete region(String regionId) {
        return (Delete) super.region(regionId);
    }

    public Delete name(String name) {
        request.setStackName(name);
        return this;
    }

    public Delete id(String stackId) {
        request.setStackId(stackId);
        return this;
    }

    public Map run() throws Exception {
        if (request.getStackId() == null || request.getStackId().equals("")) {
            Map stackInfo = stack.findOne().name(request.getStackName()).run();
            if (stackInfo != null) {
                request.setStackId((String) stackInfo.get("Id"));
            }else{
                throw new Exception("stack["+request.getStackName()+"] not found");
            }
        }
        if (request.getStackId() == null || request.getStackId().equals("")) {
            throw new Exception("stackId is null");
        }
        if (request.getStackName() == null || request.getStackName().equals("")) {
            throw new Exception("stackName is null");
        }
        return super.run();
    }
}
