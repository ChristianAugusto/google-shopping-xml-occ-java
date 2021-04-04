package com.christian.googleshoppingxmloccjava.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Price {
    public static String realBRL(Double price) {
        Locale localePtBR = new Locale("pt", "BR");

        return NumberFormat.getCurrencyInstance(localePtBR).format(price);
    }
}
