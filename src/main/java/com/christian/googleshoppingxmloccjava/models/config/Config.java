package com.christian.googleshoppingxmloccjava.models.config;

public class Config {
    private OCCConfig occConfig;
    private String storeUrl;
    private String xmlName;
    private Boolean showInactives;
    private Boolean showUnavailables;


    public OCCConfig getOccConfig() {
        return occConfig;
    }
    public void setOccConfig(OCCConfig occConfig) {
        this.occConfig = occConfig;
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
