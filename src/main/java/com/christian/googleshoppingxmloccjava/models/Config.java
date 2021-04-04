package com.christian.googleshoppingxmloccjava.models;

public class Config {
    private String occAdminHost;
    private String occAppKey;
    private String occInventoryLocation;
    private String occListProductsFields;
    private Integer occListProductsLimit;
    private String occInventoryFields;
    private String storeUrl;
    private String xmlName;
    private Boolean showInactives;
    private Boolean showUnavailables;


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
    public String getOccInventoryFields() {
        return occInventoryFields;
    }
    public void setOccInventoryFields(String occInventoryFields) {
        this.occInventoryFields = occInventoryFields;
    }
    public String getStoreUrl() {
        return storeUrl;
    }
    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }
    public String getXmlName() {
        return xmlName;
    }
    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }
    public Boolean getShowInactives() {
        return showInactives;
    }
    public void setShowInactives(Boolean showInactives) {
        this.showInactives = showInactives;
    }
    public Boolean getShowUnavailables() {
        return showUnavailables;
    }
    public void setShowUnavailables(Boolean showUnavailables) {
        this.showUnavailables = showUnavailables;
    }
}
