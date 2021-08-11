package com.wajahat.payoneerandroidtest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Getter
public class InputElement implements Parcelable {

    private final String name;
    private final String type;

    public InputElement(String name, String type) {
        this.name = name;
        this.type = type;
    }

    protected InputElement(Parcel in) {
        name = in.readString();
        type = in.readString();
    }

    public static final Creator<InputElement> CREATOR = new Creator<InputElement>() {
        @Override
        public InputElement createFromParcel(Parcel in) {
            return new InputElement(in);
        }

        @Override
        public InputElement[] newArray(int size) {
            return new InputElement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
    }
}