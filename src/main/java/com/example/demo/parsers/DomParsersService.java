package com.example.demo.parsers;

import com.example.demo.model.StaffObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by KAI on 12/14/17.
 */
public class DomParsersService {
    public void parseXmlToObject(String pathFile) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(pathFile);
        if(!file.exists())
            throw new RuntimeException("File does not exists.");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodes = document.getElementsByTagName("staff");

        Set<StaffObject> staffObjects = new HashSet<>();
        for(int i = 0; i <= nodes.getLength(); i++){
            Node node = nodes.item(i);
            StaffObject staffObject = new StaffObject();
            if(node != null)
            {
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    System.out.println("Staff id : " + element.getAttribute("id"));
                    staffObject.setId(element.getAttribute("id"));
                    staffObject.setFirstName(element.getElementsByTagName("firstname").item(0).getTextContent());
                    staffObject.setLastName(element.getElementsByTagName("lastname").item(0).getTextContent());
                    staffObject.setNickName(element.getElementsByTagName("nickname").item(0).getTextContent());
                    staffObject.setSalary(element.getElementsByTagName("salary").item(0).getTextContent());
                    staffObjects.add(staffObject);
                }
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(staffObjects));
    }
}
