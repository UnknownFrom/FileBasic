package Task2;

import java.io.IOException;

public interface IUniversity {
    public void CheckingSuitableFaculties(IStudent stud); /*проверка, на какой факультет проходит студент*/
    public void AddFaculty(IFaculty faculty);
    public void RemoveFaculty(IFaculty faculty);
    public void NotifyFaculty() throws IOException;
}
