package com.tubandev.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class KataModel implements Parcelable {

    private int id;
    private String word;
    private String description;

    public KataModel() {
    }

    public KataModel(int id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }

    public KataModel(String word, String description) {
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

    protected KataModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.description = in.readString();
    }

    public static final Creator<KataModel> CREATOR = new Creator<KataModel>() {
        @Override
        public KataModel createFromParcel(Parcel source) {
            return new KataModel(source);
        }

        @Override
        public KataModel[] newArray(int size) {
            return new KataModel[size];
        }
    };
}
