package iaac.aliyun.resource;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * 文件系统资源读取器
 * Created by jiechen on 2017/11/6.
 */
public class FileLoader extends AbstractLoader {

    /**
     * 从文件系统读取一个资源,使用当前ClassLoader读取相对路径
     * @return 资源的字符串
     * @throws Exception 资源不存在
     */
    @Override
    public String load() throws Exception {
        String result = null;
        try {
            String path = this.url;
            if (this.fileName != null) {
                path = path + "/" + this.fileName;
            }
            URL pathUrl = FileLoader.class.getClassLoader().getResource(path);
            result = FileUtils.readFileToString(new File(pathUrl.getPath()), "utf-8");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
