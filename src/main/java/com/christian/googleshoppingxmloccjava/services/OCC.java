package com.christian.googleshoppingxmloccjava.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.google.gson.Gson;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ListProductsPayload;
import com.christian.googleshoppingxmloccjava.models.occ.inventorydetails.Inventory;
import com.christian.googleshoppingxmloccjava.models.occ.inventorydetails.InventoryDetailsDefaultPayload;
import com.christian.googleshoppingxmloccjava.models.occ.inventorydetails.InventoryDetailsLocationsPayload;
import com.christian.googleshoppingxmloccjava.utils.Logger;

public class OCC {
    public static ListProductsPayload listProducts(
        String adminHost, String listProductsFields, Integer listProductsLimit,
        Boolean showInactives, long offset, OCCToken occToken, String appKey
    ) throws Exception {
        final String url = "https://{occAdminHost}/ccadmin/v1/products";

        for (int attempts = 0; attempts < 5; attempts++) {
            HttpRequest request = Unirest.get(url)
                .routeParam("occAdminHost", adminHost)
                .queryString("expand", "true")
                .queryString("fields", listProductsFields)
                .queryString("limit", listProductsLimit)
                .queryString("offset", offset)
                .header("Authorization", "Bearer ".concat(occToken.getToken(adminHost, appKey)));
            HttpResponse<String> httpResponse = null;

            if (!showInactives) {
                request.queryString("queryFormat", "SCIM")
                    .queryString("q", "active eq true AND childSKUs.active eq true");
            }

            httpResponse = request.asString();


            if (httpResponse.getStatus() / 100 != 2) {
                Logger.error(String.format(
                    "Error in OCC.listProducts: status %d, payload: %s", httpResponse.getStatus(), httpResponse.getRawBody()
                ));
            }
            else {
                Gson gson = new Gson();

                ListProductsPayload listProductsPayload = gson.fromJson(
                    httpResponse.getBody(), ListProductsPayload.class
                );

                return listProductsPayload;
            }
        }

        throw new Exception("Attempts limit exceeded in OCC.listProducts");
    }

    public static Inventory inventoryDetails(
        String adminHost, String inventoryId, String inventoryLocation, OCCToken occToken, String appKey
    ) throws Exception {
        final String url = "https://{occAdminHost}/ccadmin/v1/inventories/{inventoryId}";

        for (int attempts = 0; attempts < 5; attempts++) {
            GetRequest request = Unirest.get(url)
                .routeParam("occAdminHost", adminHost)
                .routeParam("inventoryId", inventoryId)
                .header("Authorization", "Bearer ".concat(occToken.getToken(adminHost, appKey)));

            HttpResponse<String> httpResponse = null;

            if (inventoryLocation == null) {
                httpResponse = request.queryString("fields", "stockLevel,availabilityStatusMsg")
                    .asString();


                if (httpResponse.getStatus() / 100 != 2) {
                    Logger.error(String.format(
                        "Error in OCC.inventoryDetails: status %d, payload: %s", httpResponse.getStatus(), httpResponse.getRawBody()
                    ));
                }
                else {
                    Gson gson = new Gson();

                    InventoryDetailsDefaultPayload inventoryDetailsDefaultPayload = gson.fromJson(
                        httpResponse.getBody(), InventoryDetailsDefaultPayload.class
                    );

                    Inventory inventory = new Inventory(
                        inventoryDetailsDefaultPayload.getStockLevel()
                    );

                    return inventory;
                }
            }
            else {
                httpResponse = request.queryString("locationIds", inventoryLocation)
                    .queryString("fields", "locationInventoryInfo.stockLevel")
                    .asString();


                if (httpResponse.getStatus() / 100 != 2) {
                    Logger.error(String.format(
                        "Error in OCC.inventoryDetails: status %d, payload: %s", httpResponse.getStatus(), httpResponse.getRawBody()
                    ));
                }
                else {
                    Gson gson = new Gson();

                    InventoryDetailsLocationsPayload inventoryDetailsLocationsPayload = gson.fromJson(
                        httpResponse.getBody(), InventoryDetailsLocationsPayload.class
                    );

                    Inventory inventory = new Inventory(
                        inventoryDetailsLocationsPayload.getLocationInventoryInfo().get(0).getStockLevel()
                    );

                    return inventory;
                }
            }
        }

        throw new Exception("Attempts limit exceeded in OCC.inventoryDetails");
    }
}