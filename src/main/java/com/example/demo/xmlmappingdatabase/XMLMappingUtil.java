package com.example.demo.xmlmappingdatabase;


import com.example.demo.model.Company;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class XMLMappingUtil {
    private static String JDBCDriver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String username = "root";
    private static String password = "root";
    public static Connection getInstance() throws ClassNotFoundException
            , SQLException {
        Class.forName(JDBCDriver);
        Connection connection = DriverManager
                .getConnection(url,username, password);
        return connection;
    }


    private final static String INSERT_COMPANY = "insert into staff(" +
            "id,first_name, last_name, salary, nick_name" +
            ") values(?,?,?,?,?);";

    public static void insertData(String filePath) throws IOException, SAXException, ParserConfigurationException, SQLException, ClassNotFoundException {
        Set<Company> companies = convertXMLtoObject(filePath);
        PreparedStatement statement = getInstance().prepareStatement(INSERT_COMPANY);
        companies.forEach(company -> {
            try {
                statement.setString(1, company.getId());
                statement.setString(2, company.getFirstName());
                statement.setString(3, company.getLastName());
                statement.setString(4, company.getSalary());
                statement.setString(5, company.getNickname());
                statement.execute();
            } catch (SQLException e) {
                System.out.println("SQLException "+ e.getSQLState());
            }
        });
    }

    public static Set<Company> convertXMLtoObject(String filePath) throws
            ParserConfigurationException,
            IOException,
            SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = factory
                .newDocumentBuilder();
        Document document = documentBuilder.parse(new File(filePath));
        document.getDocumentElement().normalize();
        NodeList nodes = document.getElementsByTagName("staff");

        Set<Company> companies = new HashSet<>();
        for (int i = 0; i < nodes.getLength(); i++){
            Node node = nodes.item(i);
            if(node != null && node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element)node;
                String firstName = element.getElementsByTagName("firstname")
                        .item(0).getTextContent();
                String lastName = element.getElementsByTagName("lastname")
                        .item(0).getTextContent();
                String salary = element.getElementsByTagName("salary")
                        .item(0).getTextContent();
                String nickname = element.getElementsByTagName("nickname")
                        .item(0).getTextContent();
                String id = element.getAttribute("id");
                Company company = new Company(id,firstName, lastName, nickname, salary);
                System.out.println("Company String: "+company.toString());
                companies.add(company);
            }
        }
        return companies;
    }

//    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//        convertXMLtoObject("/Users/KAI/Desktop/xml-example-project/src/main/resources/inputData.xml");
//    }
}
