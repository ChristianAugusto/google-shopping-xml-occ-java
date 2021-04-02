package com.christian.googleshoppingxmloccjava.utils;

import com.christian.googleshoppingxmloccjava.models.Config;
import com.google.gson.Gson;

public class ReadConfig {
    public static Config read(String configPath) {
        Gson gson = new Gson();

        return gson.fromJson(ReadEntireFile.read(configPath), Config.class);
    }
}
