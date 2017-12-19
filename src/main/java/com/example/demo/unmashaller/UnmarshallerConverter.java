package com.example.demo.unmashaller;


import com.example.demo.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UnmarshallerConverter {

    public String convertXMLFileToObject() throws JAXBException, JsonProcessingException {
        String filePath = "/Users/KAI/Desktop/xml-example-project/src/main/resources/out-marshaller.xml";
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filePath);
        Student student  = (Student)unmarshaller.unmarshal(file);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }

}
