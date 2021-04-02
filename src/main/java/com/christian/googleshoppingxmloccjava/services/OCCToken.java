package com.christian.googleshoppingxmloccjava.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.christian.googleshoppingxmloccjava.models.Config;
import com.christian.googleshoppingxmloccjava.models.occ.login.LoginPayload;
import com.google.gson.Gson;
import com.christian.googleshoppingxmloccjava.utils.Logger;

public class OCCToken {
    private String token;
    private Long time;
    private Long expires;
    private final static Long MARGIN_TOKEN_SECONDS = (long) 20;

    public OCCToken() {
        this.token = null;
        this.time = null;
        this.expires = null;
    }

    public String getToken(Config config) throws Exception {
        if (this.token == null) {
            this.renewToken(config);
        }
        else {
            Long secondsElapsed = (System.currentTimeMillis() - this.time) / 1000;

            if (secondsElapsed > this.expires) {
                this.renewToken(config);
            }
        }

        return this.token;
    }

    private void renewToken(Config config) throws Exception {
        final String url = "https://{occAdminHost}/ccadmin/v1/login";

        for (int attempts = 0; attempts < 5; attempts++) {
            HttpResponse<String> httpResponse = Unirest.post(url)
                .routeParam("occAdminHost", config.getOccAdminHost())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer ".concat(config.getOccAppKey()))
                .body("grant_type=client_credentials")
                .asString();


            if (httpResponse.getStatus() / 100 != 2) {
                Logger.error(String.format(
                    "Error in OCC.renewToken: status %d, payload: %s", httpResponse.getStatus(), httpResponse.getRawBody()
                ));
            }
            else {
                Gson gson = new Gson();

                LoginPayload loginPayload = gson.fromJson(
                    httpResponse.getBody(), LoginPayload.class
                );

                this.time = System.currentTimeMillis();
                this.token = loginPayload.getAccess_token();
                this.expires = loginPayload.getExpires_in() - MARGIN_TOKEN_SECONDS;

                return;
            }
        }

        throw new Exception("Attempts limit exceeded in OCC.renewToken");
    }
}
