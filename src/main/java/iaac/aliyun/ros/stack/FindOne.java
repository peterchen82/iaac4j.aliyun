package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * Created by jiechen on 2017/11/1.
 */
public class FindOne extends List {

    public FindOne(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }



    public Map run() throws Exception {
        Map<String, Object> response = super.run();
        java.util.List<Map> stacks = (java.util.List<Map>) response.get("Stacks");

        for (Map stack : stacks) {
            if(stack.get("Name").equals(request.getQueryParameters().get("Name"))){
                return stack;
            }
        }
        return null;
    }
}
