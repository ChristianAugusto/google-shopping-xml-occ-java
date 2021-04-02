package com.christian.googleshoppingxmloccjava.models.occ.listproducts;

import java.util.ArrayList;

public class ListProductsPayload {
    private ArrayList<ProductItem> items;


    public ArrayList<ProductItem> getItems() {
        return items;
    }
    public void setItems(ArrayList<ProductItem> items) {
        this.items = items;
    }
}
