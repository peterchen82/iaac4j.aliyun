package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.UpdateStackRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Update extends TemplateCommand {
    protected UpdateStackRequest request;
    private Stack stack;

    public void setStack(Stack stack) {
        this.stack = stack;
    }


    public Update(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (UpdateStackRequest) super.request;
    }

    public Update name(String name) {
        request.setStackName(name);
        return this;
    }

    public Update id(String stackId) {
        request.setStackId(stackId);
        return this;
    }

    public Map run() throws Exception {
        if (request.getStackId() == null || request.getStackId().equals("")) {
            Map stackInfo = stack.findOne().name(request.getStackName()).run();
            if (stackInfo != null) {
                request.setStackId((String) stackInfo.get("Id"));
            } else {
                throw new Exception("stack[" + request.getStackName() + "] not found");
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
