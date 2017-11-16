package iaac.aliyun.ros.stack;

import com.aliyuncs.ros.model.v20150901.*;
import iaac.aliyun.BaseApi;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.ros.event.Event;
import iaac.aliyun.ros.resource.Resource;
import iaac.aliyun.ros.template.Template;

/**
 * Created by jiechen on 2017/11/1.
 */
public class Stack extends BaseApi {

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

    public Stack(String region, ApiCredential credential) {
        super(region, credential);
    }

    public Resource resource() {
        return new Resource(region, credential);
    }

    public Template template() {
        return new Template(region, credential);
    }

    public Event event() {
        return new Event(region, credential);
    }

    public Regions regions() {
        return new Regions(new DescribeRegionsRequest(), this.region, this.credential);
    }

    public Create create() {
        return new Create(new CreateStacksRequest(), this.region, this.credential);
    }

    public Preview preview() {
        return new Preview(new PreviewStackRequest(), this.region, this.credential);
    }

    public Delete delete() {
        Delete action = new Delete(new DeleteStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    public Abandon abandon() {
        Abandon action = new Abandon(new AbandonStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    public List list() {
        return new List(new DescribeStacksRequest(), this.region, this.credential);
    }

    public FindOne findOne() {
        return new FindOne(new DescribeStacksRequest(), this.region, this.credential);
    }

    public Describe describe() {
        Describe action = new Describe(new DescribeStackDetailRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }

    public Update update() {
        Update action = new Update(new UpdateStackRequest(), this.region, this.credential);
        action.setStack(this);
        return action;
    }
}
