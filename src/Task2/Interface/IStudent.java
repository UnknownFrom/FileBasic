package Task2.Interface;

import com.itextpdf.text.DocumentException;
import org.pdfbox.exceptions.COSVisitorException;

import java.io.IOException;
import java.util.HashMap;

public interface IStudent {
    public void AddFaculties(String faculty);
    public HashMap<String, Integer> getDisciplines();
    public void ToNotify() throws IOException;
    public void ToNotifyPDF() throws IOException, COSVisitorException, DocumentException;
}
