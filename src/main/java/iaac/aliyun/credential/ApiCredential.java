package iaac.aliyun.credential;

/**
 * api请求凭据
 * Created by jiechen on 2017/11/1.
 */
public class ApiCredential {
    private String key;
    private String secret;

    /**
     * 构造api凭据
     * @param key 阿里云apiKey
     * @param secret 阿里云apiSecret
     */
    public ApiCredential(String key, String secret){
        this.key=key;
        this.secret=secret;
    }

    /**
     * 返回apiKey
     * @return apiKey
     */
    public String getKey() {
        return key;
    }
    /**
     * 返回apiSecret
     * @return apiSecret
     */
    public String getSecret() {
        return secret;
    }


}
