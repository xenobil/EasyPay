package com.example.easypay.Model;

import android.os.Bundle;

import java.io.Serializable;

import cielo.orders.domain.Order;

public class Payment implements Serializable {
    private String description, paymentFormat,textNumberParcel;
    private int numberParcel;
    private float valueProduct;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(String paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public int getNumberParcel() {
        return numberParcel;
    }

    public void setNumberParcel(int numberParcel) {
        this.numberParcel = numberParcel;
    }

    public float getValueProduct() {
        return valueProduct;
    }

    public void setValueProduct(float valueProduct) {
        this.valueProduct = valueProduct;
    }

    public String getTextNumberParcel() {
        return textNumberParcel;
    }

    public void setTextNumberParcel(String textNumberParcel) {
        this.textNumberParcel = textNumberParcel;
    }

    protected void onCreate(Bundle savedInstanceState) {

    }

    public void onPayment(Order paidOrder) {

    }

    public void addItem(String number, String name, long valueProductLong, int i, String unidade) {
    }
}