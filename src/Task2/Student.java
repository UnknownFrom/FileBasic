package Task2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements IStudent {
    private String name;
    private HashMap<String, Integer> disciplines; /*какие дисциплины сданы и баллы по ним*/
    private List<String> faculties; /*на какие факультеты поступил*/

    public Student(String name, HashMap<String, Integer> disciplines){
        this.name = name;
        this.disciplines = disciplines;
        faculties = new ArrayList<>();
    }

    @Override
    public void AddFaculties(String fac)
    {
        if(!faculties.contains(fac)) {
            faculties.add(fac);
        }
    }

    @Override
    public HashMap<String, Integer> getDisciplines() {
        return disciplines;
    }

    @Override
    public void ToNotify(String faculty) throws IOException {
        String[] FIO = name.split("_");
        String path = "C:\\Users\\Pavel\\OneDrive - vyatsu\\" +
                "5 семестр\\Java\\Lab 2\\src\\Task2\\"+FIO[0]+FIO[1]+FIO[2]+".txt";
        FileWriter fileWriter = new FileWriter(path);
        String letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] +
                ", вы поступили на следующие направления:\n";
        int i = 1;
        for (String nameFac: faculties) { /*пишем факультеты с нумерацией и без _*/
            letter += i + ") ";
            for (String s:nameFac.split("_")) {
                letter += s + " ";
            }
            letter += "\n";
            i++;
        }
        fileWriter.write(letter);
        fileWriter.flush();
    }
}
