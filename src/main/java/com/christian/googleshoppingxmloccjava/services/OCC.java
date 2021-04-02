package com.christian.googleshoppingxmloccjava.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.christian.googleshoppingxmloccjava.models.Config;
import com.google.gson.Gson;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ListProductsPayload;
import com.christian.googleshoppingxmloccjava.utils.Logger;

public class OCC {
    public static ListProductsPayload listProducts(Config config, int offset, OCCToken occToken) throws Exception {
        final String url = "https://{occAdminHost}/ccadmin/v1/products";

        for (int attempts = 0; attempts < 5; attempts++) {
            HttpResponse<String> httpResponse = Unirest.get(url)
                .routeParam("occAdminHost", config.getOccAdminHost())
                .queryString("expand", "true")
                .queryString("fields", config.getOccListProductsFields())
                .queryString("limit", config.getOccListProductsLimit())
                .queryString("offset", offset)
                .header("Authorization", "Bearer ".concat(occToken.getToken(config)))
                .asString();

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
}