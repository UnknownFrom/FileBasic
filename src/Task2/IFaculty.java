package Task2;

import java.io.IOException;
import java.util.HashMap;

public interface IFaculty {
    public String getName(); /*название факультета*/
    public HashMap<String, Integer> getDisciplines(); /*проходные баллы по дисциплинам*/
    public void AddStudent(IStudent stud);
    public void RemoveStudent(IStudent stud);
    public void NotifyStudent() throws IOException;
}
