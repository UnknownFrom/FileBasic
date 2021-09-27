package Task2;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class University implements IUniversity{
    private List<IFaculty> faculties;

    public University(){
        faculties = new ArrayList<>();
    }

    @Override
    public void CheckingSuitableFaculties(IStudent stud)
    {
        HashMap<String, Integer> disStud = stud.getDisciplines();
        for (IFaculty faculty:faculties) {
            HashMap<String, Integer> disFac = faculty.getDisciplines();
            if(disStud.size() >= disFac.size()) {
                int count = 0;
                for (String disName : disFac.keySet()) {
                    if (disStud.containsKey(disName) && disStud.get(disName) >= disFac.get(disName))
                    {
                        count++;
                    }
                }
                if(count == disFac.size())
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
    public void NotifyFaculty() {
        for (IFaculty faculty: faculties) {
            faculty.NotifyStudent();
        }
    }
}
