/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxcli.foodmenu.xzhan320.netbeans;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @disclaim http://www.programcreek.com/java-api-examples/index.php?class=org.apache.xml.serialize.OutputFormat&method=setIndent
 */
public class XMLformatBuilder {
    
    public XMLformatBuilder(){
        
    }
    
    public String format(String plainXml) {
        try {
            final Document doc = parseXmlFile(plainXml);
            OutputFormat format = new OutputFormat(doc);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            
            Writer output = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(output, format);
            serializer.serialize(doc);
            
            return output.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private Document parseXmlFile(String input) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(input));
            
            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
