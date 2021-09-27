package Task2;

import java.io.IOException;
import java.util.HashMap;

public interface IStudent {
    public void AddFaculties(String faculty);
    public HashMap<String, Integer> getDisciplines();
    public void ToNotify(String faculty) throws IOException;
}
