package Task2;

import com.itextpdf.text.DocumentException;
import org.json.simple.parser.ParseException;
import org.pdfbox.exceptions.COSVisitorException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Programm {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException, COSVisitorException, DocumentException {
        IUniversity university = new University();
        ManageExtentsion extentsion = new ManageExtentsion();
        System.out.println("Из какого формата считать файлы?\n1) TXT\n2) XML\n3) JSON");
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1:
                extentsion.setReader(new TXT());
                break;
            case 2:
                extentsion.setReader(new XML());
                break;
            case 3:
                extentsion.setReader(new JSON());
                break;
        }
        System.out.println("Введите путь к файлу факультетов:");
        String pathFaculty = new Scanner(System.in).nextLine();
        System.out.println("Введите путь к файлу студентов:");
        String pathStudent = new Scanner(System.in).nextLine();
        pathFaculty = "C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\faculty.xml";
        pathStudent = "C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2\\src\\Task2\\students.xml";
        extentsion.getReader().read(university, pathFaculty, pathStudent); /*заносим данные*/
        university.NotifyFacultyPDF(); /*высылаем оповещения по всем факультетам*/
        university.NotifyFaculty(); /*высылаем оповещения по всем факультетам*/
    }
}
