package Task2.Classes;

import Task2.Classes.Faculty;
import Task2.Classes.Student;
import Task2.Interface.IFaculty;
import Task2.Interface.IReader;
import Task2.Interface.IStudent;
import Task2.Interface.IUniversity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TXT implements IReader {
    @Override
    public void read(IUniversity university, String pathFaculty, String pathStudent) throws IOException{
        ToFillFaculties(university, pathFaculty);
        ToFillStudents(university, pathStudent);
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
