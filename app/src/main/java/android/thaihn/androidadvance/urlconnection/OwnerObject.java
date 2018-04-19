package android.thaihn.androidadvance.urlconnection;

import java.io.Serializable;

public class OwnerObject implements Serializable {
    private String login;
    private int id;
    private String avatar_url;
    private String url;
    private String repo_url;

    public OwnerObject() {
    }

    public OwnerObject(String login, int id, String avatar_url, String url, String repo_url) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.url = url;
        this.repo_url = repo_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepo_url() {
        return repo_url;
    }

    public void setRepo_url(String repo_url) {
        this.repo_url = repo_url;
    }
}
