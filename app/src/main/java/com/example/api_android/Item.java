package com.example.api_android;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    String specialty_name;
    int math_point, rus_point, itk_point, chem_point, soc_point, phys_point, en_point;

    public Item(String specialty_name, int math_point, int rus_point, int itk_point, int chem_point, int soc_point, int phys_point, int en_point) {
        this.specialty_name = specialty_name;
        this.math_point = math_point;
        this.rus_point = rus_point;
        this.itk_point = itk_point;
        this.chem_point = chem_point;
        this.soc_point = soc_point;
        this.phys_point = phys_point;
        this.en_point = en_point;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public int getMath_point() {
        return math_point;
    }

    public void setMath_point(int math_point) {
        this.math_point = math_point;
    }

    public int getRus_point() {
        return rus_point;
    }

    public void setRus_point(int rus_point) {
        this.rus_point = rus_point;
    }

    public int getItk_point() {
        return itk_point;
    }

    public void setItk_point(int itk_point) {
        this.itk_point = itk_point;
    }

    public int getChem_point() {
        return chem_point;
    }

    public void setChem_point(int chem_point) {
        this.chem_point = chem_point;
    }

    public int getSoc_point() {
        return soc_point;
    }

    public void setSoc_point(int soc_point) {
        this.soc_point = soc_point;
    }

    public int getPhys_point() {
        return phys_point;
    }

    public void setPhys_point(int phys_point) {
        this.phys_point = phys_point;
    }

    public int getEn_point() {
        return en_point;
    }

    public void setEn_point(int en_point) {
        this.en_point = en_point;
    }

    protected Item(Parcel in) {
        specialty_name = in.readString();

        math_point = in.readInt();
        rus_point = in.readInt();
        itk_point = in.readInt();
        chem_point = in.readInt();
        soc_point = in.readInt();
        phys_point = in.readInt();
        en_point = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specialty_name);

        dest.writeInt(math_point);
        dest.writeInt(rus_point);
        dest.writeInt(itk_point);
        dest.writeInt(chem_point);
        dest.writeInt(soc_point);
        dest.writeInt(phys_point);
        dest.writeInt(en_point);
    }
}
