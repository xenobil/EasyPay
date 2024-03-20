package com.example.easypay.utilidades;

import com.example.easypay.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class Util  {

    public static String getPrice(long value) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format((value / 100));
    }
}
