package android.thaihn.androidadvance.urlconnection;

import java.io.Serializable;

public class RepositoryObject implements Serializable {
    private int id;
    private String name, full_name;
    private OwnerObject owner;
    private String description;
    private boolean folk;
    private String ssh_url;
    private String language;
    private String default_branch;


    public RepositoryObject() {
    }

    public RepositoryObject(
            int id,
            String name,
            String full_name,
            OwnerObject owner,
            String description,
            boolean folk,
            String ssh_url,
            String language,
            String default_branch) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this.description = description;
        this.folk = folk;
        this.ssh_url = ssh_url;
        this.language = language;
        this.default_branch = default_branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public OwnerObject getOwner() {
        return owner;
    }

    public void setOwner(OwnerObject owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFolk() {
        return folk;
    }

    public void setFolk(boolean folk) {
        this.folk = folk;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }
}
