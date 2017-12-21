package com.example.demo;

import com.example.demo.creation.DOMCreateDocument;
import com.example.demo.creation.StAXCreateDocument;
import com.example.demo.mashaller.MashallerConvert;
import com.example.demo.model.Employees;
import com.example.demo.parsers.DomParsersService;
import com.example.demo.parsers.SAXParsersService;
import com.example.demo.unmashaller.UnmarshallerConverter;
import com.example.demo.xmlmappingdatabase.XMLMappingUtil;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class AppMain {

    private static DomParsersService domParsersService = new DomParsersService();
    private static SAXParsersService saxParsersService = new SAXParsersService();
    private static StAXCreateDocument stAXCreateDocument = new StAXCreateDocument();
    private static DOMCreateDocument dOMCreateDocument = new DOMCreateDocument();
    private static MashallerConvert mashallerConvert = new MashallerConvert();
    private static UnmarshallerConverter unmarshallerConverter = new UnmarshallerConverter();
    private static String filePath = "/Users/KAI/Desktop/xml-example-project/src/main/resources/inputData.xml";

    public static void main(String[] args) {
        try {
//        domParsersService.parseXmlToObject(filePath);
//        saxParsersService.eventParserToObject(filePath);
            String filePath = stAXCreateDocument.createEmployeeXMLDocument(new Employees());
            String filePath2 = dOMCreateDocument.createEmployeeXMLDocument(new Employees());
            System.out.println(filePath2);

            mashallerConvert.convertObjectToXMLFile();
            System.out.println(unmarshallerConverter.convertXMLFileToObject());
            XMLMappingUtil.insertData("/Users/KAI/Desktop/xml-example-project/src/main/resources/inputData.xml");

        } catch (IOException e) {
            System.out.println("[ERROR] IOException : " + e.getMessage());
        } catch (XMLStreamException e) {
            System.out.println("[ERROR] XMLStreamException: " + e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
