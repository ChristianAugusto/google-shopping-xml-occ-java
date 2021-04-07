package com.christian.googleshoppingxmloccjava.models.occ.inventorydetails;

public class Inventory {
    private Long stockLevel;


    public Inventory(Long stockLevel) {
        this.setStockLevel(stockLevel);
    }


    public Long getStockLevel() {
        return stockLevel;
    }
    public void setStockLevel(Long stockLevel) {
        this.stockLevel = stockLevel;
    }
}
