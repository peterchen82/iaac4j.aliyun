package iaac.aliyun.ros.template;

import com.aliyuncs.ros.model.v20150901.*;
import iaac.aliyun.BaseApi;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/2.
 */
public class Template extends BaseApi {


    public Template(String region, ApiCredential credential) {
        super(region, credential);
    }


    public Describe describe() {
        return new Describe(new DescribeTemplateRequest(), this.region, this.credential);
    }

    public Validate validate() {
        return new Validate(new ValidateTemplateRequest(), this.region, this.credential);
    }
}
