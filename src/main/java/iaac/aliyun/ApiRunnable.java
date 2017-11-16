package iaac.aliyun;


import java.util.Map;

/**
 * api操作命令的接口
 * Created by jiechen on 2017/11/1.
 */
public interface ApiRunnable {
    /**
     * 执行相应的api命令
     * @return 阿里云返回json转换的Map
     * @throws Exception
     */
    public Map run() throws Exception;
}
