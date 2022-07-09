package postsite.postsitespring.domain.user.domain;

public class User {
    private Long id;
    private String loginId;
    private String loginPw;

    public User(){

    }
    public User(Long id, String loginId, String loginPw) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLoginId() { return loginId; }

    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getLoginPw() { return loginPw; }

    public void setLoginPw(String loginPw) { this.loginPw = loginPw; }
}
