package com.christian.googleshoppingxmloccjava.models.occ.inventorydetails;

import java.util.ArrayList;

public class InventoryDetailsLocationsPayload {
    private ArrayList<LocationInventoryInfoItem> locationInventoryInfo;


    public ArrayList<LocationInventoryInfoItem> getLocationInventoryInfo() {
        return locationInventoryInfo;
    }
    public void setLocationInventoryInfo(ArrayList<LocationInventoryInfoItem> locationInventoryInfo) {
        this.locationInventoryInfo = locationInventoryInfo;
    }
}
