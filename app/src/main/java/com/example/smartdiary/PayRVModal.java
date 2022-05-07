package com.example.smartdiary;

import android.os.Parcel;
import android.os.Parcelable;

public class PayRVModal implements Parcelable {
    private String day;
    private String food;
    private String transport;
    private String snacks;
    private String expenses;
    private String PayID;

    public PayRVModal(String day, String transport, String snacks, String expenses, String payID){

    }

    protected PayRVModal(Parcel in) {
        day = in.readString();
        food = in.readString();
        transport = in.readString();
        snacks = in.readString();
        expenses = in.readString();
        PayID = in.readString();
    }

    public static final Creator<PayRVModal> CREATOR = new Creator<PayRVModal>() {
        @Override
        public PayRVModal createFromParcel(Parcel in) {
            return new PayRVModal(in);
        }

        @Override
        public PayRVModal[] newArray(int size) {
            return new PayRVModal[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getPayID() {
        return PayID;
    }

    public void setPayID(String payID) {
        PayID = payID;
    }

    public PayRVModal(String day, String food, String transport, String snacks, String expenses, String payID) {
        this.day = day;
        this.food = food;
        this.transport = transport;
        this.snacks = snacks;
        this.expenses = expenses;
        PayID = payID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(day);
        parcel.writeString(food);
        parcel.writeString(transport);
        parcel.writeString(snacks);
        parcel.writeString(expenses);
        parcel.writeString(PayID);
    }
}
