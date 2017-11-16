package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.credential.ApiCredential;

/**
 * 创建资源栈命令
 * Created by jiechen on 2017/11/1.
 */
public class Create extends TemplateCommand {
    /**
     * 构造创建资源栈命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public Create(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

}
