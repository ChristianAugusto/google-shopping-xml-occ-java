package com.christian.googleshoppingxmloccjava;

import com.christian.googleshoppingxmloccjava.models.Config;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ListProductsPayload;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ProductItem;
import com.christian.googleshoppingxmloccjava.services.OCC;
import com.christian.googleshoppingxmloccjava.services.OCCToken;
import com.christian.googleshoppingxmloccjava.utils.Logger;
import com.christian.googleshoppingxmloccjava.utils.ReadConfig;

public class App {
	public static void main(String[] args) {
		try {
            Logger.info("Starting Google shopping XML OCC Java");



            String configPath = System.getenv("CONFIG_PATH");

            if (configPath == null) {
                throw new Exception("configPath is null");
            }

            Logger.info(String.format("configPath = %s", configPath));

            Config config = ReadConfig.read(configPath);

            if (config == null) {
                throw new Exception("config is null");
            }

            OCCToken occToken = new OCCToken();
            int offset = 0;
            while (true) {
                ListProductsPayload listProductsPayload = OCC.listProducts(config, offset, occToken);

                if (listProductsPayload.getItems() == null || listProductsPayload.getItems().size() == 0) {
                    break;
                }


                for (ProductItem productItem : listProductsPayload.getItems()) {
                    System.out.println(productItem.getRepositoryId());
                    System.out.println(productItem.getDisplayName());
                }

                offset += config.getOccListProductsLimit();
                break; // tmp
            }



            Logger.info("Finishing Google shopping XML OCC Java");
        }
        catch (Exception e) {
            Logger.info("Error in Google shopping XML OCC Java");
            e.printStackTrace();
        }
	}
}
