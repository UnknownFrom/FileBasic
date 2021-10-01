package Task2.Classes;

import Task2.Interface.IReader;
import Task2.Interface.IUniversity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class JSON implements IReader {
    @Override
    public void read(IUniversity university, String pathFaculty, String pathStudent) throws IOException, ParseException {
        ToReadFacultiesFromJSON(university, pathFaculty);
        ToReadStudentsFromJSON(university, pathStudent);
    }

    private static void ToReadStudentsFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("students");
        Iterator i = disciplineJ.iterator();
        while(i.hasNext())
        {
            HashMap<String, Integer> discipline = new HashMap<>();
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

    private static void ToReadFacultiesFromJSON(IUniversity university, String path) throws IOException, ParseException {
        FileReader reader = new FileReader(new File(path));
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONArray disciplineJ = (JSONArray) jsonObject.get("faculties");
        Iterator i = disciplineJ.iterator();
        while(i.hasNext())
        {
            HashMap<String, Integer> disciplines = new HashMap<>();
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
}
