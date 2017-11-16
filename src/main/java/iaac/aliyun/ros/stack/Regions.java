package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 返回可用区列表命令
 * Created by jiechen on 2017/11/1.
 */
public class Regions extends ApiRequest {
    public Regions(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

}
