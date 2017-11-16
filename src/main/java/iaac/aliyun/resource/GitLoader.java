package iaac.aliyun.resource;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.util.Date;

/**
 * git资源读取器,从给定的git地址和文件名获取资源
 * Created by jiechen on 2017/11/6.
 */
public class GitLoader extends AbstractLoader {

    private String branch = "master";
    private String localPath = System.getProperty("java.io.tmpdir");

    /**
     * 设置git分支
     *
     * @param branch 分支名
     * @return 自身引用
     */
    public GitLoader setBranch(String branch) {
        this.branch = branch;
        return this;
    }

    /**
     * 从指定的git仓库地址(目前仅支持http和https)和文件名获取资源,通过UsernameCredential支持鉴权
     *
     * @return 资源的字符串
     * @throws Exception
     */
    @Override
    public String load() throws Exception {
        //本地临时目录,用户存放clone的代码
        String tempDirPath = localPath + "/iaac.aliyun.tmp_" + new Date().getTime();
        File tempDir = new File(tempDirPath);
        tempDir.mkdirs();
        String result = null;
        try {
            CloneCommand clone = Git.cloneRepository();
            clone.setURI(url);
            clone.setBranch(this.branch);
            clone.setDirectory(tempDir);

            //设置鉴权
            if (this.credential != null) {
                UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                        UsernamePasswordCredentialsProvider(this.credential.getUsername(), this.credential.getPassword());
                //git仓库地址
                clone.setCredentialsProvider(usernamePasswordCredentialsProvider);
            }
            //执行clone
            Git git = clone.call();
            //从本地路径中获取指定的文件
            File file = new File(tempDir.getAbsolutePath() + "/" + this.fileName);
            //返回文件的字符串
            result = FileUtils.readFileToString(file, "utf-8");
        } catch (Exception e) {
            throw e;
        } finally {
            //清除本地的git临时目录
            FileUtils.deleteDirectory(tempDir);
        }
        return result;
    }
}
