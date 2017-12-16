package com.example.demo.creation;


import com.example.demo.BaseConstant;
import com.example.demo.model.Employees;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMCreateDocument extends BaseCommon{

    public String createEmployeeXMLDocument(Employees employees) throws ParserConfigurationException, TransformerException {

        employees = prepareData();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        //create root
        Element element = document.createElement(BaseConstant.EMPLOYEE_ELEMENT);
        element.setAttribute(BaseConstant.ID_ELEMENT, employees.getId());

        Element nameElement = document.createElement(BaseConstant.NAME_ELEMENT);
        nameElement.appendChild(document.createTextNode(employees.getName()));
        element.appendChild(nameElement);

        Element salaryElement = document.createElement(BaseConstant.SALARY_ELEMENT);
        salaryElement.appendChild(document.createTextNode(employees.getSalary()));
        element.appendChild(salaryElement);

        Element addressElement = document.createElement(BaseConstant.ADDRESS_ELEMENT);
        addressElement.appendChild(document.createTextNode(employees.getAddress()));
        element.appendChild(addressElement);


        document.appendChild(element);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        File file = new File("employee_dom.xml");

        StreamResult destination = new StreamResult(file);
        transformer.transform(source, destination);
        return file.getAbsolutePath();
    }
}
