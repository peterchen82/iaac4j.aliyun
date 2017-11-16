package iaac.aliyun.credential;

/**
 * 基于用户名和口令的凭据
 * Created by jiechen on 2017/11/1.
 */
public class UsernameCredential {
    private String username;
    private String password;

    /**
     * 构造凭据
     * @param username 用户名
     * @param password 口令
     */
    public UsernameCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 返回用户名
     * @return 用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 返回口令
     * @return 口令
     */
    public String getPassword() {
        return this.password;
    }


}
