package Task2.Interface;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IReader {
    public void read(IUniversity university, String pathFaculty, String pathStudent) throws ParserConfigurationException, IOException, SAXException, ParseException;
}
