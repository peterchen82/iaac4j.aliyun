package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.DeleteStackRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * 删除一个资源栈命令
 * Created by jiechen on 2017/11/1.
 */
public class Delete extends ApiRequest {
    protected DeleteStackRequest request;

    private Stack stack;
    /**
     * stack对象的注入方法,用户在内部调用stack其它的api
     * @param stack stack对象实例
     */
    public void setStack(Stack stack) {
        this.stack = stack;
    }

    /**
     * 构造删除资源栈命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Delete(DeleteStackRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (DeleteStackRequest) super.request;
    }

    /**
     * 设置要删除资源栈的可用区id
     * @param regionId 可用区标识符,Regions类中提供了可用区的常量池
     * @return
     */
    public Delete region(String regionId) {
        return (Delete) super.region(regionId);
    }

    /**
     * 设置资源栈名,删除指定名字的资源栈.可以只指定资源栈名不指定资源栈id,同名取第一个符合条件的(阿里云不允许资源栈同名:))
     * @param name 资源栈名
     * @return
     */
    public Delete name(String name) {
        request.setStackName(name);
        return this;
    }
    /**
     * 设置资源栈id,删除指定id的资源栈
     * @param stackId 资源栈id
     * @return
     */
    public Delete id(String stackId) {
        request.setStackId(stackId);
        return this;
    }
    /**
     * 删除一个资源栈,删除后资源栈创建的资源也会被释放.不指定资源栈id的情况下可直接通过资源栈名废除.
     * @return 结果
     * @throws Exception
     */
    public Map run() throws Exception {
        //没有设置stackId时,通过stackName先查询出stackId
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
