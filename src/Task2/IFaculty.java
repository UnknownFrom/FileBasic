package Task2;

import java.util.Dictionary;
import java.util.HashMap;

public interface IFaculty {
    public String getName();
    public HashMap<String, Integer> getDisciplines();
    public void AddStudent(IStudent stud);
    public void RemoveStudent(IStudent stud);
    public void NotifyStudent();
}
