package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.ros.model.v20150901.UpdateStackRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * 更新资源栈命令
 * Created by jiechen on 2017/11/1.
 */
public class Update extends TemplateCommand {
    protected UpdateStackRequest request;
    private Stack stack;

    /**
     * stack对象的注入方法,用户在内部调用stack其它的api
     * @param stack stack对象实例
     */
    public void setStack(Stack stack) {
        this.stack = stack;
    }

    /**
     * 构造删更新源栈命令对象
     * @param request    阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region     可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Update(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
        this.request = (UpdateStackRequest) super.request;
    }

    /**
     * 设置资源栈名,更新指定名字的资源栈.可以只指定资源栈名不指定资源栈id,同名取第一个符合条件的(阿里云不允许资源栈同名:))
     *
     * @param name 资源栈名
     * @return 自身引用
     */
    public Update name(String name) {
        request.setStackName(name);
        return this;
    }

    /**
     * 设置资源栈id,更新指定id的资源栈
     *
     * @param stackId 资源栈id
     * @return 自身引用
     */
    public Update id(String stackId) {
        request.setStackId(stackId);
        return this;
    }

    /**
     * 更新一个资源栈
     *
     * @return 更新后的结果
     * @throws Exception 没有提供stackId或资源名没有找到以及服务器端返回了不正确的信息
     */
    public Map run() throws Exception {
        //没有设置stackId时,通过stackName先查询出stackId
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
