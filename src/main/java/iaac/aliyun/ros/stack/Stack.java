package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.*;
import iaac.aliyun.BaseApi;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.ros.event.Event;
import iaac.aliyun.ros.resource.Resource;
import iaac.aliyun.ros.template.Template;

/**
 * 资源栈相关api
 * Created by jiechen on 2017/11/1.
 */
public class Stack extends BaseApi {
    //资源栈状态常量
    public class Status {
        public static final String CREATE_COMPLETE = "CREATE_COMPLETE";
        public static final String CREATE_FAILED = "CREATE_FAILED";
        public static final String CREATE_IN_PROGRESS = "CREATE_IN_PROGRESS";
        public static final String DELETE_FAILED = "DELETE_FAILED";
        public static final String DELETE_IN_PROGRESS = "DELETE_IN_PROGRESS";
        public static final String ROLLBACK_COMPLETE = "ROLLBACK_COMPLETE";
        public static final String ROLLBACK_FAILED = "ROLLBACK_FAILED";
        public static final String ROLLBACK_IN_PROGRESS = "ROLLBACK_IN_PROGRESS";

    }

    /**
     * 构造资源栈api对象
     *
     * @param region     可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Stack(String region, ApiCredential credential) {
        super(region, credential);
    }

    public Resource resource() {
        return new Resource(region, credential);
    }

    /**
     * 返回模板api对象
     *
     * @return 模板api对象
     */
    public Template template() {
        return new Template(region, credential);
    }

    public Event event() {
        return new Event(region, credential);
    }

    /**
     * 返回可用区api对象
     *
     * @return 可用区api对象
     */
    public Regions regions() {
        return new Regions(new DescribeRegionsRequest(), this.region, this.credential);
    }

    /**
     * 返回创建资源栈命令
     *
     * @return 创建资源栈命令对象
     */
    public Create create() {
        return new Create(new CreateStacksRequest(), this.region, this.credential);
    }

    /**
     * 返回预览资源栈模板命令
     *
     * @return 预览资源栈模板命令对象
     */
    public Preview preview() {
        return new Preview(new PreviewStackRequest(), this.region, this.credential);
    }

    /**
     * 返回删除资源栈命令
     *
     * @return 删除资源栈命令对象
     */
    public Delete delete() {
        Delete action = new Delete(new DeleteStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    /**
     * 返回废除资源栈命令
     *
     * @return 废除资源栈命令对象
     */
    public Abandon abandon() {
        Abandon action = new Abandon(new AbandonStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    /**
     * 返回资源栈列表命令
     *
     * @return 资源栈列表命令对象
     */
    public List list() {
        return new List(new DescribeStacksRequest(), this.region, this.credential);
    }

    /**
     * 返回查找单个资源栈命令
     *
     * @return 查找单个资源栈命令对象
     */
    public FindOne findOne() {
        return new FindOne(new DescribeStacksRequest(), this.region, this.credential);
    }

    /**
     * 返回查看资源栈详情命令
     *
     * @return 资源栈详情命令对象
     */
    public Describe describe() {
        Describe action = new Describe(new DescribeStackDetailRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    /**
     * 返回更新命令
     *
     * @return 更新命令对象
     */
    public Update update() {
        Update action = new Update(new UpdateStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }
}
