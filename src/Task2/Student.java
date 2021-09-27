package Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class Student implements IStudent {
    private String name;
    private HashMap<String, Integer> disciplines;
    private List<String> faculties;

    public Student(String name, HashMap<String, Integer> disciplines){
        this.name = name;
        this.disciplines = disciplines;
        faculties = new ArrayList<>();
    }

    @Override
    public void AddFaculties(String fac)
    {
        faculties.add(fac);
    }

    @Override
    public HashMap<String, Integer> getDisciplines() {
        return disciplines;
    }

    @Override
    public void ToNotify(String faculty) throws IOException {
        String[] FIO = name.split("_");
        FileWriter fileWriter = new FileWriter("C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\"+FIO[0]+FIO[1]+FIO[2]);
        String letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] + " , вы поступили на следующие направления:\n";
        int i = 1;
        for (String nameFac: faculties) {
            letter += i + ") " + nameFac+"\n";
        }
        fileWriter.write(letter);
    }
}
