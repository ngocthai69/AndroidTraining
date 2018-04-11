package android.thaihn.androidadvance.recycleview;

import java.io.Serializable;

public class ContactObject implements Serializable{

    private String mName;
    private String mEmail;

    public ContactObject(String mName, String mEmail) {
        this.mName = mName;
        this.mEmail = mEmail;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
