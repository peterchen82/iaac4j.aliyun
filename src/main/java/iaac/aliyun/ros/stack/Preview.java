package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Preview extends TemplateCommand {

    public Preview(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }
}
