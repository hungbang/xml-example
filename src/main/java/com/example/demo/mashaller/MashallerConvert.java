package com.example.demo.mashaller;


import com.example.demo.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MashallerConvert {
    public String convertObjectToXMLFile() throws JAXBException {
        Student student = prepareDataObject();
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        File file = new File("out-marshaller.xml");
        marshaller.marshal(student, new File("out-marshaller.xml"));
        return file.getAbsolutePath();
    }

    private Student prepareDataObject() {
       return Student.StudentBuilder.aStudent()
               .withId(1)
               .withName("name").build();
    }
}
