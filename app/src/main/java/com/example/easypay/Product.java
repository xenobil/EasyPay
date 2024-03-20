package com.example.easypay;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price) {
        super();
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
