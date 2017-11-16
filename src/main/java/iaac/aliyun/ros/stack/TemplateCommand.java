package iaac.aliyun.ros.stack;

import com.aliyuncs.RoaAcsRequest;
import iaac.aliyun.ApiRequest;
import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.resource.Loader;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by jiechen on 2017/11/1.
 */
public class TemplateCommand extends ApiRequest {

    public TemplateCommand(RoaAcsRequest request, String region, ApiCredential credential) {
        super(request, region, credential);
    }

    public TemplateCommand name(String name) {
        props.put("Name", name);
        return this;
    }

    public TemplateCommand template(String template) {
        props.put("Template", template);
        return this;
    }

    public TemplateCommand loadTemplate(Loader loader) throws Exception {
        props.put("Template", loader.load());
        return this;
    }

    public TemplateCommand parameters(String name, Object value) {
        params.put(name, value);
        return this;
    }

    public TemplateCommand loadParameters(Loader loader) throws Exception {
        String content = loader.load();
        JSONObject json = new JSONObject(content);
        Map data = params.toMap();
        data.putAll(json.toMap());
        params = new JSONObject(data);
        return this;
    }

    public TemplateCommand disableRollback(String disableRollback) {
        props.put("DisableRollback", disableRollback);
        return this;
    }

    public TemplateCommand timeoutMins(int timeoutMins) {
        props.put("TimeoutMins", timeoutMins);
        return this;
    }

}
