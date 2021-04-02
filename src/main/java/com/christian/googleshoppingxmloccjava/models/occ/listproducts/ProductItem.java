package com.christian.googleshoppingxmloccjava.models.occ.listproducts;

import java.util.ArrayList;

public class ProductItem {
    private String repositoryId;
    private String displayName;
    private String route;
    private Boolean active;
    private Boolean excludeFromSitemap;
    private ArrayList<ChildSKU> childSKUs;


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
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Boolean getExcludeFromSitemap() {
        return excludeFromSitemap;
    }
    public void setExcludeFromSitemap(Boolean excludeFromSitemap) {
        this.excludeFromSitemap = excludeFromSitemap;
    }
    public ArrayList<ChildSKU> getChildSKUs() {
        return childSKUs;
    }
    public void setChildSKUs(ArrayList<ChildSKU> childSKUs) {
        this.childSKUs = childSKUs;
    }
}
