package Task2;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XML implements IReader{

    @Override
    public void read(IUniversity university, String pathFaculty, String pathStudent) throws ParserConfigurationException, IOException, SAXException {
        ToReadFacultiesFromXML(university, pathFaculty);
        ToReadStudentsFromXML(university, pathStudent);
    }

    private static void ToReadStudentsFromXML(IUniversity university, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        /*получение списка всех элементов student*/
        NodeList studentsElements = document.getDocumentElement().getElementsByTagName("student");

        /*перебор всех элементов students*/
        for(int i = 0; i < studentsElements.getLength(); i++)
        {
            Node student = studentsElements.item(i);
            NamedNodeMap attributes = student.getAttributes();
            HashMap<String, Integer> disciplines = new HashMap<>();
            String name = attributes.getNamedItem("name").getNodeValue();
            String[] discipline = attributes.getNamedItem("disciplines").getNodeValue().split(" ");
            String[] score = attributes.getNamedItem("scores").getNodeValue().split(" ");
            for (int k = 0; k < discipline.length; k++)
            {
                disciplines.put(discipline[k],Integer.parseInt(score[k]));
            }
            university.CheckingSuitableFaculties(new Student(name, disciplines));
        }
    }

    private static void ToReadFacultiesFromXML(IUniversity university, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        /*получение списка всех элементов faculties*/
        NodeList facultiesElements = document.getDocumentElement().getElementsByTagName("faculty");

        /*перебор всех элементов faculties*/
        for(int i = 0; i < facultiesElements.getLength(); i++)
        {
            Node faculty = facultiesElements.item(i);
            NamedNodeMap attributes = faculty.getAttributes();
            HashMap<String, Integer> disciplines = new HashMap<>();
            String name =  attributes.getNamedItem("name").getNodeValue();
            String[] discipline = attributes.getNamedItem("disciplines").getNodeValue().split(" ");
            String[] score = attributes.getNamedItem("scores").getNodeValue().split(" ");
            for (int k = 0; k < discipline.length; k++)
            {
                disciplines.put(discipline[k],Integer.parseInt(score[k]));
            }
            university.AddFaculty(new Faculty(name, disciplines));
        }
    }
}
