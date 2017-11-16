package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.DescribeStackDetailRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * 查询资源栈详情命令
 * Created by jiechen on 2017/11/1.
 */
public class Describe extends ApiRequest {
    protected DescribeStackDetailRequest request;

    private Stack stack;
    /**
     * stack对象的注入方法,用户在内部调用stack其它的api
     * @param stack stack对象实例
     */
    public void setStack(Stack stack) {
        this.stack = stack;
    }

    /**
     * 构造查询资源栈详情命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Describe(DescribeStackDetailRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DescribeStackDetailRequest) super.request;
    }
    /**
     * 设置资源栈名,查询指定名字的资源栈详情.可以只指定资源栈名不指定资源栈id,同名取第一个符合条件的(阿里云不允许资源栈同名:))
     * @param name 资源栈名
     * @return
     */
    public Describe name(String name) {
        request.setStackName(name);
        return this;
    }
    /**
     * 设置资源栈id,查询指定id的资源栈详情
     * @param stackId 资源栈id
     * @return
     */
    public Describe id(String stackId) {
        request.setStackId(stackId);
        return this;
    }
    public Map run() throws Exception {
        //没有设置stackId时,通过stackName先查询出stackId
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
