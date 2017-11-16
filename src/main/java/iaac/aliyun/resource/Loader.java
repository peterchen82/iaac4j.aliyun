package iaac.aliyun.resource;

import iaac.aliyun.credential.UsernameCredential;

/**
 * 资源读取的抽象接口
 * Created by jiechen on 2017/11/6.
 */
public interface Loader {

    /**
     * 设置凭据
     * @param credential 用户名/口令类凭据
     * @return 自身引用
     */
    public Loader credential(UsernameCredential credential);

    /**
     * 设置资源的位置
     * @param url 资源url
     * @param fileName 资源名
     * @return 自身引用
     */
    public Loader location(String url,String fileName);

    /**
     * 设置资源的位置
     * @param url 资源url
     * @return 自身引用
     */
    public Loader location(String url);

    /**
     * 读取资源
     * @return 资源的字符串形式
     * @throws Exception 无法获取资源发生的异常
     */
    public String load() throws Exception;
}
