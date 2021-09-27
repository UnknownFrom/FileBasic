package Task2;

import java.io.*;
import java.util.HashMap;

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

public class Programm {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        IUniversity university = new University();
        //ToFillFaculties(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty.TXT");
        //ToFillStudents(university, "C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students.TXT");
        ToReadFromXML(university, "C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\all.xml");
        university.NotifyFaculty(); /*высылаем оповещения по всем факультетам*/
    }

    public static void ToReadFromXML(IUniversity university, String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        /*получение списка всех элементов student*/
        NodeList studentsElements = document.getDocumentElement().getElementsByTagName("students");

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
                disciplines.put(discipline[i],Integer.parseInt(score[i]));
            }
            university.CheckingSuitableFaculties(new Student(name, disciplines));
        }

        /*получение списка всех элементов faculties*/
        NodeList facultiesElements = document.getDocumentElement().getElementsByTagName("faculties");

        /*перебор всех элементов faculties*/
        for(int i = 0; i < facultiesElements.getLength(); i++)
        {
            Node faculty = facultiesElements.item(i);
            NamedNodeMap attributes = faculty.getAttributes();
            HashMap<String, Integer> disciplines = new HashMap<>();
            String name = attributes.getNamedItem("name").getNodeValue();
            String[] discipline = attributes.getNamedItem("disciplines").getNodeValue().split(" ");
            String[] score = attributes.getNamedItem("scores").getNodeValue().split(" ");
            for (int k = 0; k < discipline.length; k++)
            {
                disciplines.put(discipline[i],Integer.parseInt(score[i]));
            }
            university.AddFaculty(new Faculty(name, disciplines));
        }
    }

    public static void ToFillFaculties(IUniversity university, String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String s;
        while ((s = reader.readLine()) != null) { /*считываем и добавляем всю информацию о факультете*/
            String[] spl = s.split(" ");
            university.AddFaculty(ToCreateFaculty(spl));
        }
    }

    public static void ToFillStudents(IUniversity university, String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String s;
        while ((s = reader.readLine()) != null) { /*считываем и добавляем всю информацию о студенте*/
            String[] spl = s.split(" ");
            university.CheckingSuitableFaculties(ToCreateStudents(spl));
        }
    }
    public static IFaculty ToCreateFaculty(String[] s)
    {
        String name = s[0];
        HashMap<String, Integer> disciplines = new HashMap<>();
        for (int i = 1; i < s.length; i+=2)
        {
            disciplines.put(s[i],Integer.parseInt(s[i+1]));
        }
        IFaculty faculty = new Faculty(name, disciplines);
        return faculty;
    }

    public static IStudent ToCreateStudents(String[] s)
    {
        String name = s[0];
        HashMap<String, Integer> disciplines = new HashMap<>();
        for (int i = 1; i < s.length; i+=2)
        {
            disciplines.put(s[i], Integer.parseInt(s[i+1]));
        }
        IStudent student = new Student(name, disciplines);
        return student;
    }

}
