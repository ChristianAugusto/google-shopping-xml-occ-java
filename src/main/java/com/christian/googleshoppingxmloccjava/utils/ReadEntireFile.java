package com.christian.googleshoppingxmloccjava.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadEntireFile {
    public static String read(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            br.close();

            return sb.toString();
        } 
        catch (Exception e) {
            Logger.error(e.getMessage());
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    Logger.info(ioe.getMessage());
                }
            }
            return null;
        }
    }
}
