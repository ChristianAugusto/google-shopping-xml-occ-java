package com.christian.googleshoppingxmloccjava.utils;

import java.util.HashMap;
import java.util.Map;
import com.christian.googleshoppingxmloccjava.models.files.XML;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ChildSKU;
import com.christian.googleshoppingxmloccjava.models.occ.listproducts.ProductItem;

public class GoogleShoppingXML {
    private static Map<String, String> rssAttributes() {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("xmlns:g", "http://base.google.com/ns/1.0");
        attributes.put("version", "2.0");

        return attributes;
    }

    public static void start(XML xml) throws Exception {
        xml.writeOpeningElement("rss", rssAttributes());
        xml.writeOpeningElement("channel", null);
    }

    public static void writeItem(XML xml, String storeUrl, ProductItem productItem, ChildSKU childSKU, Boolean available) throws Exception {
        xml.writeOpeningElement("item", null);
        xml.writeElementWithContent("g:item_group_id", null, productItem.getRepositoryId());
        xml.writeElementWithContent("g:id", null, childSKU.getRepositoryId());
        xml.writeElementWithContent("g:title", null, childSKU.getDisplayName());
        xml.writeElementWithContent(
            "g:description", null, productItem.getDescription() == null ? "" : productItem.getDescription()
        );
        xml.writeElementWithContent(
            "g:product_type", null,
            productItem.getParentCategory() == null ? "" : productItem.getParentCategory().getRepositoryId()
        );
        xml.writeElementWithContent("g:brand", null, productItem.getBrand());
        xml.writeElementWithContent("g:link", null, String.format(
            "%s%s", storeUrl, productItem.getRoute()
        ));
        xml.writeElementWithContent("g:image_link", null, String.format(
            "%s%s", storeUrl, productItem.getPrimaryLargeImageURL()
        ));
        xml.writeElementWithContent("g:price", null, Price.realBRL(childSKU.getListPrice()));
        xml.writeElementWithContent("g:sale_price", null, Price.realBRL(childSKU.getSalePrice()));
        xml.writeElementWithContent(
            "g:availability", null, available ? "in stock" : "out of stock"
        );
        xml.writeElementWithContent("g:condition", null, "new");
        xml.writeClosingElement("item");
    }

    public static void finish(XML xml) throws Exception {
        xml.writeClosingElement("channel");
        xml.writeClosingElement("rss");
    }
}
