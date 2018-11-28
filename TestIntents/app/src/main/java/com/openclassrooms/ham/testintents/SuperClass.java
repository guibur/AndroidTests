package com.openclassrooms.ham.testintents;

import android.os.Parcel;
import android.os.Parcelable;

public class SuperClass implements Parcelable {
    private int mInt;
    private String mString;
    private SubClass mSubClass;

    SuperClass(int i, String s){
        this.mInt = i;
        this.mString = s;
        this.mSubClass = new SubClass("ijkl", 6);
    }

    SuperClass(Parcel in){
        this.mInt = in.readInt();
        this.mString = in.readString();
        this.mSubClass = in.readParcelable(SubClass.class.getClassLoader());
    }

    @Override
    public int describeContents(){
        return 0; // No file descriptor.
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mInt);
        dest.writeString(this.mString);
        dest.writeParcelable(this.mSubClass, 0);
    }

    public static final Parcelable.Creator<SuperClass> CREATOR = new Parcelable.Creator<SuperClass>(){
        @Override
        public SuperClass createFromParcel(Parcel source) {
            return new SuperClass(source);
        }

        @Override
        public SuperClass[] newArray(int size) {
            return new SuperClass[size];
        }
    };

    public int getmInt(){
        return mInt;
    }
    public String getmString(){
        return mString;
    }
    public SubClass getmSubClass(){
        return mSubClass;
    }
}
