package iaac.aliyun.ros.resource;

import com.aliyuncs.ros.model.v20150901.*;
import iaac.aliyun.BaseApi;
import iaac.aliyun.credential.ApiCredential;

/**
 * 资源栈中资源相关的api
 * Created by jiechen on 2017/11/2.
 */
public class Resource extends BaseApi {

    //资源状态
    public class SupportStatus {
        public static final String UNKNOWN = "UNKNOWN";
        public static final String SUPPORTED = "SUPPORTED";
        public static final String DEPRECATED = "DEPRECATED";
        public static final String UNSUPPORTED = "UNSUPPORTED";
        public static final String HIDDEN = "HIDDEN";
    }

    /**
     * 通过可用区id和ApiCredential构造
     * @param region 可用区id
     * @param credential ApiCredential
     */
    public Resource(String region, ApiCredential credential) {
        super(region, credential);
    }
    /**
     * 返回资源列表命令对象
     * @return 资源列表命令对象
     */
    public List list() {
        return new List(new DescribeResourcesRequest(), this.region, this.credential);
    }
    /**
     * 返回资源类型列表命令对象
     * @return 资源类型列表命令对象
     */
    public TypeList typeList() {
        return new TypeList(new DescribeResourceTypesRequest(), this.region, this.credential);
    }
    /**
     * 返回资源类型详情命令对象
     * @return 资源类型详情命令对象
     */
    public TypeDescribe typeDescribe() {
        return new TypeDescribe(new DescribeResourceTypeDetailRequest(), this.region, this.credential);
    }
    /**
     * 返回资源类型模板命令对象
     * @return 资源类型模板命令对象
     */
    public TypeTemplate typeTemplate() {
        return new TypeTemplate(new DescribeResourceTypeTemplateRequest(), this.region, this.credential);
    }

    /**
     * 返回资源详情命令对象
     * @return 资源详情命令对象
     */
    public Describe describe() {
        return new Describe(new DescribeResourceDetailRequest(), this.region, this.credential);
    }
}
