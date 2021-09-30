package Task2;

import java.io.*;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.util.Iterator;

public class Programm {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {
        IUniversity university = new University();
        //ToFillFaculties(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty.TXT");
        //ToFillStudents(university, "C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students.TXT");
        //ToReadFacultiesFromXML(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty.xml");
        //ToReadStudentsFromXML(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students.xml");
        ToReadFacultiesFromJSON(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty2.json");
        ToReadStudentsFromJSON(university,"C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students2.json");
        university.NotifyFaculty(); /*высылаем оповещения по всем факультетам*/
    }

    public static void ToReadStudentsFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("students");
        HashMap<String, Integer> discipline = new HashMap<>();
        Iterator i = disciplineJ.iterator();
        while(i.hasNext())
        {
            JSONObject obj = (JSONObject)i.next();
            String name = (String)obj.get("name");
            String[]dis = ((String)obj.get("discipline")).split(" ");
            for (int k = 0; k < dis.length; k+=2)
            {
                discipline.put(dis[k],Integer.parseInt(dis[k+1]));
            }
            university.CheckingSuitableFaculties(new Student(name,discipline));
        }
    }

    public static void ToReadFacultiesFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("faculties");
        HashMap<String, Integer> disciplines = new HashMap<>();
        Iterator i = disciplineJ.iterator();
        while(i.hasNext())
        {
            JSONObject obj = (JSONObject)i.next();
            String name = (String)obj.get("name");
            String[]dis = ((String)obj.get("discipline")).split(" ");
            for (int k = 0; k < dis.length; k+=2)
            {
                disciplines.put(dis[k],Integer.parseInt(dis[k+1]));
            }
            university.AddFaculty(new Faculty(name, disciplines));
        }
    }

    public static void ToReadStudentsFromXML(IUniversity university, String path) throws ParserConfigurationException, IOException, SAXException {
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

    public static void ToReadFacultiesFromXML(IUniversity university, String path) throws ParserConfigurationException, IOException, SAXException {
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
