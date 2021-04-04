package com.christian.googleshoppingxmloccjava.models.occ.listproducts;

public class ChildSKU {
    private String repositoryId;
    private String displayName;
    private Double listPrice;
    private Double salePrice;
    private Boolean active;


    public String getRepositoryId() {
        return repositoryId;
    }
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Double getListPrice() {
        return listPrice;
    }
    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }
    public Double getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
