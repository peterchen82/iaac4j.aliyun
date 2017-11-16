package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.credential.ApiCredential;

import java.util.Map;

/**
 * 查询资源栈命令
 * Created by jiechen on 2017/11/1.
 */
public class FindOne extends List {
    /**
     * 构造查询资源栈命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public FindOne(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }


    /**
     * 查询给定名字的资源栈详情
     * @return 资源栈详情
     * @throws Exception
     */
    public Map run() throws Exception {
        Map<String, Object> response = super.run();
        java.util.List<Map> stacks = (java.util.List<Map>) response.get("Stacks");

        for (Map stack : stacks) {
            if(stack.get("Name").equals(request.getQueryParameters().get("Name"))){
                return stack;
            }
        }
        return null;
    }
}
