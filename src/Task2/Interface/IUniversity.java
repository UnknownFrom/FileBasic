package Task2.Interface;

import Task2.Interface.IFaculty;
import Task2.Interface.IStudent;
import com.itextpdf.text.DocumentException;
import org.pdfbox.exceptions.COSVisitorException;

import java.io.IOException;
import java.util.List;

public interface IUniversity {
    public void CheckingSuitableFaculties(IStudent stud); /*проверка, на какой факультет проходит студент*/
    public List<IFaculty> getFaculties();
    public List<IStudent> getStudents();
    public void AddStudent(IStudent student);
    public void AddFaculty(IFaculty faculty);
    public void RemoveFaculty(IFaculty faculty);
    public void NotifyFaculty() throws IOException, COSVisitorException, DocumentException;
    public void NotifyFacultyPDF() throws IOException, COSVisitorException, DocumentException;
}
