package com.christian.googleshoppingxmloccjava;

import com.christian.googleshoppingxmloccjava.models.Config;
import com.christian.googleshoppingxmloccjava.models.files.XML;
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
            XML xml = new XML("build", config.getXmlName());



            GoogleShoppingXML.start(xml);

            while (true) {
                ListProductsPayload listProductsPayload = OCC.listProducts(config, offset, occToken);

                if (listProductsPayload.getItems() == null || listProductsPayload.getItems().size() == 0) {
                    break;
                }


                for (ProductItem productItem : listProductsPayload.getItems()) {
                    try {
                        if (
                            productItem.getActive() ||
                            (!productItem.getActive() && config.getShowInactives())
                        ) {
                            ArrayList<ChildSKU> childSKUs = productItem.getChildSKUs();

                            if (childSKUs != null && childSKUs.size() > 0) {
                                for (ChildSKU childSKU : childSKUs) {
                                    try {
                                        if (
                                            childSKU.getActive() ||
                                            (!childSKU.getActive() && config.getShowInactives())
                                        ) {
                                            // TODO: Validar estoque
                                            GoogleShoppingXML.writeItem(config, xml, productItem, childSKU, true);
                                        }
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                        throw new Exception(String.format(
                                            "Error in process sku %s", childSKU.getRepositoryId()
                                        ));
                                    }
                                }
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

                offset += config.getOccListProductsLimit();
                break; // tmp
            }

            GoogleShoppingXML.finish(xml);



            xml.finish();



            // TODO: Tempo de execução
            Logger.info("Finishing Google shopping XML OCC Java");
        }
        catch (Exception e) {
            Logger.info("Error in Google shopping XML OCC Java");
            e.printStackTrace();
        }
	}
}
