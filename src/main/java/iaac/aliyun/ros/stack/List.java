package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * Created by jiechen on 2017/11/1.
 */
public class List extends ApiRequest {

    public List(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

    public List name(String name) {
        request.putQueryParameter("Name", name);
        return this;
    }

    public List id(String stackId) {
        request.putQueryParameter("StackId", stackId);
        return this;
    }
    public List status(String status) {
        request.putQueryParameter("Status", status);
        return this;
    }
    public List page(int pageNumber) {
        request.putQueryParameter("PageNumber", pageNumber);
        return this;
    }
    public List size(int pageSize) {
        request.putQueryParameter("PageSize", pageSize);
        return this;
    }
}
