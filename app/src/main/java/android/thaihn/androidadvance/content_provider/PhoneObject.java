package android.thaihn.androidadvance.content_provider;

import java.io.Serializable;

public class PhoneObject implements Serializable {

    private String mId;
    private int mAvatar;
    private String mName;
    private String mNumber;

    public PhoneObject(String mId, int avatar, String name, String number) {
        this.mId = mId;
        this.mAvatar = mAvatar;
        this.mName = name;
        this.mNumber = number;
    }

    public String getmId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }
}
