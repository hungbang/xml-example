package com.example.demo.parsers;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by KAI on 12/14/17.
 */
public class SAXParsersService {


    public void eventParserToObject(String filePath) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filePath);
        if(!file.exists())
            throw new RuntimeException("File does not exists.");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(file, new CustomDefaultHandler());
    }

}
