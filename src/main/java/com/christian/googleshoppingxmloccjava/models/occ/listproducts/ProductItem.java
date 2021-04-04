package com.christian.googleshoppingxmloccjava.models.occ.listproducts;

import java.util.ArrayList;

public class ProductItem {
    private String repositoryId;
    private String description;
    private ParentCategory parentCategory;
    private String brand;
    private String route;
    private Boolean excludeFromSitemap;
    private Boolean active;
    private String primaryLargeImageURL;
    private ArrayList<ChildSKU> childSKUs;


    public String getRepositoryId() {
        return repositoryId;
    }
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ParentCategory getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(ParentCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public Boolean getExcludeFromSitemap() {
        return excludeFromSitemap;
    }
    public void setExcludeFromSitemap(Boolean excludeFromSitemap) {
        this.excludeFromSitemap = excludeFromSitemap;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getPrimaryLargeImageURL() {
        return primaryLargeImageURL;
    }
    public void setPrimaryLargeImageURL(String primaryLargeImageURL) {
        this.primaryLargeImageURL = primaryLargeImageURL;
    }
    public ArrayList<ChildSKU> getChildSKUs() {
        return childSKUs;
    }
    public void setChildSKUs(ArrayList<ChildSKU> childSKUs) {
        this.childSKUs = childSKUs;
    }
}
