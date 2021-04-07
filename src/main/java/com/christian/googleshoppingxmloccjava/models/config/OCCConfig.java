package com.christian.googleshoppingxmloccjava.models.config;

public class OCCConfig {
    private String adminHost;
    private String appKey;
    private String inventoryLocation;
    private String listProductsFields;
    private Integer listProductsLimit;
    private String inventoryFields;


    public String getAdminHost() {
        return adminHost;
    }
    public void setAdminHost(String adminHost) {
        this.adminHost = adminHost;
    }
    public String getAppKey() {
        return appKey;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public String getInventoryLocation() {
        return inventoryLocation;
    }
    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }
    public String getListProductsFields() {
        return listProductsFields;
    }
    public void setListProductsFields(String listProductsFields) {
        this.listProductsFields = listProductsFields;
    }
    public Integer getListProductsLimit() {
        return listProductsLimit;
    }
    public void setListProductsLimit(Integer listProductsLimit) {
        this.listProductsLimit = listProductsLimit;
    }
    public String getInventoryFields() {
        return inventoryFields;
    }
    public void setInventoryFields(String inventoryFields) {
        this.inventoryFields = inventoryFields;
    }
}
