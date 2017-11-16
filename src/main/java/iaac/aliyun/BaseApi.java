package iaac.aliyun;

import iaac.aliyun.credential.ApiCredential;
import iaac.aliyun.resource.FileLoader;
import iaac.aliyun.resource.GitLoader;
import iaac.aliyun.resource.HttpLoader;
import iaac.aliyun.resource.Loader;

/**
 * api门面的抽象类,定义了所有api共同的默认行为
 * Created by jiechen on 2017/11/1.
 */
public abstract class BaseApi {
    protected ApiCredential credential;
    protected String region;
    /**
     * 通过可用区id和credential构造
     * @param region 可用区id
     * @param credential api鉴权信息
     */
    public BaseApi(String region, ApiCredential credential) {
        this.credential = credential;
        this.region = region;
    }

    /**
     * 返回一个GitLoader的新实例,可通过配置gitLoader实例来连接git资源
     * @return GitLoader实例
     */
    public GitLoader gitLoader(){
        return new GitLoader();
    }
    /**
     * 返回一个HttpLoader的新实例,可通过配置gitLoader实例来连接git资源
     * @return HttpLoader实例
     */
    public HttpLoader httpLoader(){
        return new HttpLoader();
    }
    /**
     * 返回一个GitLoader的新实例,可通过配置gitLoader实例来连接git资源
     * @return GitLoader实例
     */
    public FileLoader fileLoader(){
        return new FileLoader();
    }
}
