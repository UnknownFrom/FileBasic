package Task2;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Programm {

    public static void main(String[] args) throws IOException {
        IUniversity university = new University();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty.TXT"));
        String s;
        //String[] s = reader.readLine().split(" ");
        while ((s = reader.readLine()) != null) {
            String[] spl = s.split(" ");
            university.AddFaculty(ToReadFaculty(spl));
        }
        reader = new BufferedReader(new FileReader("C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students.TXT"));
        while ((s = reader.readLine()) != null) {
            String[] spl = s.split(" ");
            university.CheckingSuitableFaculties(ToReadStudents(spl));
        }
        university.NotifyFaculty();
    }

    public static IFaculty ToReadFaculty(String[] s)
    {
        String name = s[0];
        HashMap<String, Integer> dic = new HashMap<>();
        for (int i = 1; i < s.length; i+=2)
        {
            dic.put(s[i],Integer.parseInt(s[i+1]));
        }
        IFaculty faculty = new Faculty(name, dic);
        return faculty;
    }

    public static IStudent ToReadStudents(String[] s)
    {
        String name = s[0];
        HashMap<String, Integer> dic = new HashMap<>();
        for (int i = 1; i < s.length; i+=2)
        {
            dic.put(s[i],Integer.parseInt(s[i+1]));
        }
        IStudent student = new Student(name, dic);
        return student;
    }

}
