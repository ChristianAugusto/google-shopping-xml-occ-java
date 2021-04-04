package com.christian.googleshoppingxmloccjava.models.files;

import com.christian.googleshoppingxmloccjava.utils.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XML {
    private String name;
    private String path;
    private FileWriter xmlWriter;


    public XML(String buildDir, String name) throws Exception {
        this.setName(name.endsWith(".xml") ? name : name.concat(".xml"));
        this.setPath(String.format("%s/%s", buildDir, name));

        File xml = new File(
            this.getPath()
        );

        if (xml.createNewFile()) {
            Logger.info(String.format(
                "XML created: %s", this.getPath()
            ));
        }
        else {
            Logger.info("XML already exists");
        }

        this.setXmlWriter(new FileWriter(this.getPath()));
    }

    public void writeOpeningElement(String elementName, Map<String, String> attributes) throws Exception {
        String tag = "<".concat(elementName);

        if (attributes != null) {
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                tag = tag.concat(String.format(
                    " %s=\"%s\"", attribute.getKey(), attribute.getValue()
                ));
            }
        }

        tag = tag.concat(">");

        this.getXmlWriter().write(tag);
    }

    public void writeClosingElement(String elementName) throws Exception {
        String tag = String.format("</%s>", elementName);

        this.getXmlWriter().write(tag);
    }

    public void writeElementWithContent(String elementName, Map<String, String> attributes, String content) throws Exception {
        this.writeOpeningElement(elementName, attributes);

        this.getXmlWriter().write(
            String.format("<![CDATA[ %s ]]>", content)
        );

        this.writeClosingElement(elementName);
    }

    public void finish() throws Exception {
        this.getXmlWriter().close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileWriter getXmlWriter() {
        return xmlWriter;
    }

    public void setXmlWriter(FileWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }
}
