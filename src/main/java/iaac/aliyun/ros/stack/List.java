package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 返回资源栈列表命令
 * Created by jiechen on 2017/11/1.
 */
public class List extends ApiRequest {
    /**
     * 构造查询资源栈命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public List(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

    /**
     * 设置资源栈名,过滤出指定名字的资源栈.
     * @param name 资源栈名
     * @return 自身引用
     */
    public List name(String name) {
        request.putQueryParameter("Name", name);
        return this;
    }
    /**
     * 设置资源栈id,过滤出指定id的资源栈.
     * @param stackId 资源栈id
     * @return 自身引用
     */
    public List id(String stackId) {
        request.putQueryParameter("StackId", stackId);
        return this;
    }
    /**
     * 设置资源栈状态,过滤出指定状态的资源栈.
     * @param status 资源栈状态id
     * @return 自身引用
     */
    public List status(String status) {
        request.putQueryParameter("Status", status);
        return this;
    }
    /**
     * 设置分页数
     * @param pageNumber 分页数
     * @return 自身引用
     */
    public List page(int pageNumber) {
        request.putQueryParameter("PageNumber", pageNumber);
        return this;
    }
    /**
     * 设置每次请求返回的条数
     * @param pageSize 返回的条数
     * @return 自身引用
     */
    public List size(int pageSize) {
        request.putQueryParameter("PageSize", pageSize);
        return this;
    }
}
