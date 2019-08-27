package kz.softrackmfs.epaystub.domain.utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class ParsedDocument {

    private final String documentAsString;
    private final Document document;

    public ParsedDocument(String documentAsString) {
        this.documentAsString = documentAsString;
        this.document = convertStringToXMLDocument(documentAsString);
    }

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            //API to obtain DOM Document instance
            //Create DocumentBuilder with default configuration
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            return builder.parse(new InputSource(new StringReader(xmlString)));
        }
        catch (Exception e) {
            throw new RuntimeException("failed to parse string \n:" + xmlString + "\n",  e);
        }
    }

    public String getByXPath(String expr) {
        try {
            return XPathFactory.newInstance().newXPath().compile(expr).evaluate(document);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
