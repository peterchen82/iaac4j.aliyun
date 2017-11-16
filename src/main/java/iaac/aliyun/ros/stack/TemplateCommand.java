package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.resource.Loader;
import org.json.JSONObject;

import java.util.Map;

/**
 * 资源编排模板相关命令的抽象类,定义所有依赖资源编排模板命令的共同行为
 * Created by jiechen on 2017/11/1.
 */
public class TemplateCommand extends ApiRequest {
    /**
     * 构造函数
     *
     * @param request    阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region     可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public TemplateCommand(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

    /**
     * 设置模板名
     *
     * @param name 模板名
     * @return 自身引用
     */
    public TemplateCommand name(String name) {
        props.put("Name", name);
        return this;
    }

    /**
     * 设置模板内容
     *
     * @param template 模板内容字符串
     * @return 自身引用
     */
    public TemplateCommand template(String template) {
        props.put("Template", template);
        return this;
    }

    /**
     * 设置模板读取器,通过读取器读取模板资源
     *
     * @param loader 资源读取器实例
     * @return 自身引用
     * @throws Exception 资源不存在或网络问题造成无法读取资源的异常
     */
    public TemplateCommand loadTemplate(Loader loader) throws Exception {
        props.put("Template", loader.load());
        return this;
    }

    /**
     * 设置资源编排模板的参数,请参考阿里云ROS api文档
     *
     * @param name  参数名
     * @param value 参数值
     * @return 自身引用
     */
    public TemplateCommand parameters(String name, Object value) {
        params.put(name, value);
        return this;
    }

    /**
     * 设置参数读取器,通过读取器读取参数资源
     *
     * @param loader 资源读取器实例
     * @return 自身引用
     * @throws Exception 资源不存在或网络问题造成无法读取资源的异常*
     */
    public TemplateCommand loadParameters(Loader loader) throws Exception {
        String content = loader.load();
        JSONObject json = new JSONObject(content);
        Map data = params.toMap();
        data.putAll(json.toMap());
        params = new JSONObject(data);
        return this;
    }

    /**
     * 禁用失败回滚
     *
     * @param disableRollback 是否禁用失败回滚
     * @return 自身引用
     */
    public TemplateCommand disableRollback(boolean disableRollback) {
        props.put("DisableRollback", String.valueOf(disableRollback));
        return this;
    }

    /**
     * 设置资源栈创建/更新的超时时间,单位分钟
     *
     * @param timeoutMins 超时分钟数
     * @return 自身引用
     */
    public TemplateCommand timeoutMins(int timeoutMins) {
        props.put("TimeoutMins", timeoutMins);
        return this;
    }

}
