package QQServer.common;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID=1L; // 增加兼容性
    private String userId;
    private String passwd;

    public String getUserId() {
        return userId;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
}