package Task2;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class Faculty implements IFaculty{
    private List<IStudent> students;
    private String name;
    private HashMap<String, Integer> disciplines;

    public Faculty(String name, HashMap<String, Integer> disciplines)
    {
        students = new ArrayList<>();
        this.name = name;
        this.disciplines = disciplines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HashMap<String, Integer> getDisciplines() {
        return disciplines;
    }

    @Override
    public void AddStudent(IStudent stud) {
        students.add(stud);
    }

    @Override
    public void RemoveStudent(IStudent stud) {
        students.remove(stud);
    }

    @Override
    public void NotifyStudent() {
        for (IStudent student: students) {
            student.ToNotify(name);
        }
    }
}