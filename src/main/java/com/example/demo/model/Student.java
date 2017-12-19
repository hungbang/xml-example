package com.example.demo.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    private int id;
    private String name;
    private String className;
    private String address;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static final class StudentBuilder {
        private int id;
        private String name;
        private String className;
        private String address;

        private StudentBuilder() {
        }

        public static StudentBuilder aStudent() {
            return new StudentBuilder();
        }

        public StudentBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public StudentBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setClassName(className);
            student.setAddress(address);
            return student;
        }
    }
}
