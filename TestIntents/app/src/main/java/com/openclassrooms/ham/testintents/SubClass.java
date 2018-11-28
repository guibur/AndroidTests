package com.openclassrooms.ham.testintents;

import android.os.Parcel;
import android.os.Parcelable;

public class SubClass implements Parcelable {
    String ms = "def";
    int mi = 3;

    SubClass(String s, int i){
        this.ms = s;
        this.mi = i;
    }

    SubClass(Parcel in){
        this.ms = in.readString();
        this.mi = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ms);
        dest.writeInt(this.mi);
    }


    public static final Parcelable.Creator<SubClass> CREATOR = new Parcelable.Creator<SubClass>(){
        @Override
        public SubClass createFromParcel(Parcel source) {
            return new SubClass(source);
        }

        @Override
        public SubClass[] newArray(int size) {
            return new SubClass[size];
        }
    };

    public String getMs(){
        return this.ms;
    }
    public int getMi(){
        return this.mi;
    }
}
