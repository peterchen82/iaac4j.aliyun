package iaac.aliyun.ros.template;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Validate extends ApiRequest {

    public Validate(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

    public Validate template(String template) {
        props.put("Template", template);
        return this;
    }
}
