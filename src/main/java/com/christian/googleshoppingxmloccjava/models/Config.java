package com.christian.googleshoppingxmloccjava.models;

public class Config {
    private String occAdminHost;
    private String occAppKey;
    private String occInventoryLocation;
    private String occListProductsFields;
    private Integer occListProductsLimit;


    public String getOccAdminHost() {
        return occAdminHost;
    }
    public void setOccAdminHost(String occAdminHost) {
        this.occAdminHost = occAdminHost;
    }
    public String getOccAppKey() {
        return occAppKey;
    }
    public void setOccAppKey(String occAppKey) {
        this.occAppKey = occAppKey;
    }
    public String getOccInventoryLocation() {
        return occInventoryLocation;
    }
    public void setOccInventoryLocation(String occInventoryLocation) {
        this.occInventoryLocation = occInventoryLocation;
    }
    public String getOccListProductsFields() {
        return occListProductsFields;
    }
    public void setOccListProductsFields(String occListProductsFields) {
        this.occListProductsFields = occListProductsFields;
    }
    public Integer getOccListProductsLimit() {
        return occListProductsLimit;
    }
    public void setOccListProductsLimit(Integer occListProductsLimit) {
        this.occListProductsLimit = occListProductsLimit;
    }
}
