package com.example.demo.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by KAI on 12/14/17.
 */
public class CustomDefaultHandler extends DefaultHandler {
    boolean firstNameFlag = false;
    boolean lastNameFlag = false;
    boolean nickNameFlag = false;
    boolean salaryNameFlag = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("firstname")) {
            firstNameFlag = true;
        }
        if (qName.equalsIgnoreCase("lastname")) {
            lastNameFlag = true;
        }
        if (qName.equalsIgnoreCase("nickname")) {
            nickNameFlag = true;
        }
        if (qName.equalsIgnoreCase("salary")) {
            salaryNameFlag = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End Element: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (firstNameFlag) {
            System.out.println("First Name : " + new String(ch, start, length));
            firstNameFlag = false;
        }
        if (lastNameFlag) {
            System.out.println("Last Name : " + new String(ch, start, length));
            lastNameFlag = false;
        }
        if (nickNameFlag) {
            System.out.println("Nick Name : " + new String(ch, start, length));
            nickNameFlag = false;
        }
        if (salaryNameFlag) {
            System.out.println("Salary : " + new String(ch, start, length));
            salaryNameFlag = false;
        }
    }

}
