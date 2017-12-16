package com.example.demo;

import com.example.demo.parsers.DomParsersService;
import com.example.demo.parsers.SAXParsersService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by KAI on 12/14/17.
 */
public class AppMain {

    private static DomParsersService domParsersService = new DomParsersService();
    private static SAXParsersService saxParsersService = new SAXParsersService();
    private static String filePath = "/Users/KAI/Desktop/xml-example-project/src/main/resources/inputData.xml";
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        domParsersService.parseXmlToObject(filePath);
        saxParsersService.eventParserToObject(filePath);
    }
}
