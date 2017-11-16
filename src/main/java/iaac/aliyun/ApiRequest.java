package iaac.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RoaAcsRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import iaac.aliyun.credential.ApiCredential;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * 阿里云Ros中每个操作命令的抽象,完成所有命令共同的设置
 * Created by jiechen on 2017/11/1.
 */
public abstract class ApiRequest implements ApiRunnable {

    //阿里云api中基础的对象,请参考阿里云api文档
    protected IClientProfile profile;
    protected IAcsClient client;
    protected RoaAcsRequest request;

    //阿里云api要求的body请求,请参考阿里云api文档
    protected JSONObject props = new JSONObject();
    //body请求中Parameters部分,请参考阿里云api文档
    protected JSONObject params = new JSONObject();

    /**
     * 构造一个api命令对象
     * @param request 阿里云Request,可接受各类阿里云RoaAcsRequest的子类
     * @param region 可用区标识符,Regions类中提供了可用区的常量池
     * @param credential api鉴权信息
     */
    public ApiRequest(RoaAcsRequest request, String region, ApiCredential credential) {
        this.profile = DefaultProfile.getProfile(region, credential.getKey(), credential.getSecret());
        this.client = new DefaultAcsClient(this.profile);
        this.request = request;
        this.request.putHeaderParameter("x-acs-region-id", region);
    }

    /**
     * 设置可用区
     * @param regionId 可用区标识符,Regions类中提供了可用区的常量池
     * @return 自身引用
     */
    public ApiRequest region(String regionId) {
        request.putHeaderParameter("x-acs-region-id", regionId);
        return this;
    }

    @Override
    public Map run() throws Exception {
        //处理body参数
        if (props.keySet().size() > 0) {
            if (params.keySet().size() > 0) {
                props.put("Parameters", params);
            }
            request.setHttpContent(props.toString().getBytes(), "UTF-8", FormatType.JSON);
        }
        HttpResponse res = client.doAction(request);
        String jsonString = new String(res.getHttpContent());
        try{
            JSONObject json;
            if (jsonString != null && !jsonString.equals("")) {
                //判断返回结果是否是一个json数组
                if(jsonString.startsWith("[") && jsonString.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(jsonString);
                    json = new JSONObject();
                    json.put("List",jsonArray);  //把数组放入返回结果的List字段中
                }else{
                    json = new JSONObject(jsonString);
                }
            } else {
                //返回空json对象
                json = new JSONObject();
            }
            //返回Map，消除外部程序对json api的编译依赖
            return json.toMap();
        }catch(JSONException jsonException){
            JSONObject error = new JSONObject();
            error.put("Message",jsonString);
            return error.toMap();
        }
    }

}
