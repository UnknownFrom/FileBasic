package Task2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class University implements IUniversity{
    private List<IFaculty> faculties;

    public University(){
        faculties = new ArrayList<>();
    }

    @Override
    public void CheckingSuitableFaculties(IStudent stud) /*проверка, на какой факультет проходит студент*/
    {
        HashMap<String, Integer> disStud = stud.getDisciplines();
        for (IFaculty faculty:faculties) { /*проходим по факультетам университета*/
            HashMap<String, Integer> disFac = faculty.getDisciplines(); /*получаем список дисциплин факультета*/
            if(disStud.size() >= disFac.size()) {
                int count = 0;
                for (String disName : disFac.keySet()) { /*проходим по дисциплинам факультета*/
                    if (disStud.containsKey(disName) && disStud.get(disName) >= disFac.get(disName))
                    /*если у студента сдана эта дисциплина и балл достаточный*/
                    {
                        count++;
                    }
                }
                if(count == disFac.size())
                /*если все дисциплины для факультета у студента сданы*/
                {
                    stud.AddFaculties(faculty.getName());
                    faculty.AddStudent(stud);
                }
            }
        }
    }

    @Override
    public void AddFaculty(IFaculty faculty) {
        faculties.add(faculty);
    }

    @Override
    public void RemoveFaculty(IFaculty faculty) {
        faculties.remove(faculty);
    }

    @Override
    public void NotifyFaculty() throws IOException {
        for (IFaculty faculty: faculties) {
            faculty.NotifyStudent();
        }
    }
}
