package com.wajahat.payoneerandroidtest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Getter
public class Networks implements Parcelable {

    @Setter
    private List<ApplicableNetwork> applicable;

    protected Networks(Parcel in) {
    }

    public static final Creator<Networks> CREATOR = new Creator<Networks>() {
        @Override
        public Networks createFromParcel(Parcel in) {
            return new Networks(in);
        }

        @Override
        public Networks[] newArray(int size) {
            return new Networks[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}