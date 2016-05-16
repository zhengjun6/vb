package adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/5/15.
 */
public class data implements Parcelable {
    private int id;
    private String title;

    public data(int id, String title) {
        this.id = id;
        this.title = title;
    }

    protected data(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel in) {
            return new data(in);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }
}
