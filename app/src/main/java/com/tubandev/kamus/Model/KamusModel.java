package com.tubandev.kamus.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class KamusModel implements Parcelable {

    private int id;
    private String word;
    private String description;

    public KamusModel() {
    }

    public KamusModel(int id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }

    public KamusModel(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.description);
    }

    protected KamusModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.description = in.readString();
    }

    public static final Creator<KamusModel> CREATOR = new Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}
