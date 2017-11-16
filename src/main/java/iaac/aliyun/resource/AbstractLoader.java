package iaac.aliyun.resource;

import iaac.aliyun.credential.UsernameCredential;
import org.json.JSONObject;

/** 资源读取器的抽象类,完成一些通用设置
 * Created by jiechen on 2017/11/6.
 */
public abstract class AbstractLoader implements Loader{
    protected UsernameCredential credential;
    protected String url;
    protected String fileName;

    /**
     * 通过UsernameCredential构造
     * @param credential 用户名/口令类凭据
     * @return
     */
    public Loader credential(UsernameCredential credential) {
        this.credential = credential;
        return this;
    }
    public Loader location(String url, String fileName) {
        this.url=url;
        this.fileName=fileName;
        return this;
    }
    public Loader location(String url) {
        this.url=url;
        return this;
    }
}
