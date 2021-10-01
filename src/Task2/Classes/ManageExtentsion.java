package Task2.Classes;

import Task2.Interface.IReader;

public class ManageExtentsion {
    private IReader reader;

    public IReader getReader(){return  reader;}

    public void setReader(IReader reader) {
        this.reader = reader;
    }
}
