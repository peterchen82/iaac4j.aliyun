package iaac.aliyun.resource;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Http资源读取器,给定
 * Created by jiechen on 2017/11/6.
 */
public class HttpLoader extends AbstractLoader {

    @Override
    public String load() throws Exception {
        String result = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder
                    .url(this.url)
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6").build();
            //设置鉴权信息
            if (this.credential != null) {
                //TODO 实现http basic认证
            }
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
