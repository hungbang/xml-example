package com.example.demo.creation;

import com.example.demo.BaseConstant;
import com.example.demo.model.Employees;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

public class StAXCreateDocument extends BaseCommon{



    public String createEmployeeXMLDocument(Employees employees) throws XMLStreamException, IOException {
        employees = prepareData();
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = factory.createXMLStreamWriter(stringWriter);

        xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement(BaseConstant.EMPLOYEE_ELEMENT);
                xmlStreamWriter.writeAttribute(BaseConstant.ID_ELEMENT, employees.getId());

                xmlStreamWriter.writeStartElement(BaseConstant.NAME_ELEMENT);
                xmlStreamWriter.writeCharacters(employees.getName());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(BaseConstant.SALARY_ELEMENT);
                xmlStreamWriter.writeCharacters(employees.getSalary());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement(BaseConstant.ADDRESS_ELEMENT);
                xmlStreamWriter.writeCharacters(employees.getAddress());
                xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();

        xmlStreamWriter.flush();
        xmlStreamWriter.close();

        String data = stringWriter.getBuffer().toString();
        stringWriter.flush();

        String pathFile = writeStringToFile(data);

        return pathFile;
    }

    private String writeStringToFile(String data) throws IOException {
        File file = new File("employees.xml");
        if(file.exists())
            return file.getAbsolutePath();

        byte[] bytes = data.getBytes();

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();

        return file.getAbsolutePath();
    }
}
