package com.christian.googleshoppingxmloccjava;

import com.christian.googleshoppingxmloccjava.models.config.Config;
import com.christian.googleshoppingxmloccjava.models.files.XML;
import com.christian.googleshoppingxmloccjava.models.occ.inventorydetails.Inventory;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ChildSKU;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ListProductsPayload;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ProductItem;
import com.christian.googleshoppingxmloccjava.services.OCC;
import com.christian.googleshoppingxmloccjava.services.OCCToken;
import com.christian.googleshoppingxmloccjava.utils.GoogleShoppingXML;
import com.christian.googleshoppingxmloccjava.utils.Logger;
import com.christian.googleshoppingxmloccjava.utils.ReadConfig;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		try {
            Logger.info("Starting Google shopping XML OCC Java");
            long initTime = System.currentTimeMillis();



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
            long offset = 0;
            long totalProducts = 0;
            long totalSkus = 0;
            long writtenProducts = 0;
            long writtenSkus = 0;
            XML xml = new XML("build", config.getXmlName());



            GoogleShoppingXML.start(xml);

            while (true) {
                ListProductsPayload listProductsPayload = OCC.listProducts(
                    config.getOccConfig().getAdminHost(), config.getOccConfig().getListProductsFields(),
                    config.getOccConfig().getListProductsLimit(), config.getShowInactives(), offset,
                    occToken, config.getOccConfig().getAppKey()
                );

                if (listProductsPayload.getItems() == null || listProductsPayload.getItems().size() == 0) {
                    break;
                }

                totalProducts += listProductsPayload.getItems().size();

                for (ProductItem productItem : listProductsPayload.getItems()) {
                    try {
                        ArrayList<ChildSKU> childSKUs = productItem.getChildSKUs();

                        if (childSKUs != null && childSKUs.size() > 0) {
                            boolean writtenProduct = false;

                            for (ChildSKU childSKU : childSKUs) {
                                try {
                                    totalSkus++;

                                    Inventory inventory = OCC.inventoryDetails(
                                        config.getOccConfig().getAdminHost(), childSKU.getRepositoryId(),
                                        config.getOccConfig().getInventoryLocation(),
                                        occToken, config.getOccConfig().getAppKey()
                                    );

                                    if (
                                        inventory.getStockLevel() > 0 ||
                                        (inventory.getStockLevel() == 0 && config.getShowUnavailables())
                                    ) {
                                        GoogleShoppingXML.writeItem(xml, config.getStoreUrl(), productItem, childSKU, true);
                                        writtenProduct = true;
                                        writtenSkus++;
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                    throw new Exception(String.format(
                                        "Error in process sku %s", childSKU.getRepositoryId()
                                    ));
                                }
                            }

                            if (writtenProduct) {
                                writtenProducts++;
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception(String.format(
                            "Error in process product %s", productItem.getRepositoryId()
                        ));
                    }
                }

                Logger.info(String.format("Finishing offset %d", offset));
                Logger.info(String.format("totalProducts = %d", totalProducts));
                Logger.info(String.format("totalSkus = %d", totalSkus));
                Logger.info(String.format("writtenProducts = %d", writtenProducts));
                Logger.info(String.format("writtenSkus = %d", writtenSkus));
                Logger.info("------------------------------------------------------------------------");

                offset += config.getOccConfig().getListProductsLimit();

                System.gc();
            }

            GoogleShoppingXML.finish(xml);



            xml.finish();



            long finishTime = System.currentTimeMillis();
            long executionTime = (finishTime - initTime) / 1000;

            Logger.info(String.format("Execution time %d seconds", executionTime));
            Logger.info("Finishing Google shopping XML OCC Java");
        }
        catch (Exception e) {
            Logger.info("Error in Google shopping XML OCC Java");
            e.printStackTrace();
        }
	}
}
