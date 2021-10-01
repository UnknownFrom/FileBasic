import java.io.File;

public class WorkingDirectory {
    private static WorkingDirectory instance;
    private String directoryName;

    private WorkingDirectory(String directoryName) {
        this.directoryName = directoryName;
    }

    public static WorkingDirectory getInstance(String directoryName) {
        if (instance == null) {
            instance = new WorkingDirectory(directoryName);
        }
        return instance;
    }

    public void ToPrint()
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i<s.length;i++)
        {
            System.out.println(s[i]);
        }
    }

    public void ToPrintPath()
    {
        System.out.println(directoryName);
    }

    public void ToParentName()
    {
        String s = new File(directoryName).getParent();
        System.out.println(s.substring(s.lastIndexOf("\\")));
    }

    public void ToParent()
    {
        directoryName = new File(directoryName).getParent();
    }

    public boolean checkChildDir(String childName)
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i < s.length; i++)
        {
            if (new File(directoryName + "\\" + s[i]).isDirectory() && s[i].equals(childName)) {
                return true;
            }
        }
        return false;
    }

    public void ToCreateSubdir(String name)
    {
        File file = new File(directoryName + "\\" + name);
        file.mkdir();
    }

    public void ToChildDir(String name)
    {
        File file = new File(directoryName + "\\" + name);
        if(file.isDirectory()) {
            directoryName = directoryName + "\\" + name;
        }
    }

    public void DeleteAllDir()
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i < s.length; i++)
        {
            if (new File(directoryName + "\\" + s[i]).isDirectory()) {
                directoryName = directoryName + "\\" + s[i];
                DeleteAllDir();
            }
            File nFile = new File(directoryName + "\\" + s[i]);
            nFile.delete();
        }
        directoryName = file.getParent();
    }

    public void ToFindEx(String ex)
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i< s.length; i++)
        {
            if(s[i].endsWith(ex))
            {
                System.out.println(s[i]);
            }
        }
    }

    public void ToPrintSubdir(String out)
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i < s.length; i++)
        {
            if (new File(directoryName + "\\" + s[i]).isDirectory()) {
                System.out.println(out+s[i]);
                directoryName = directoryName + "\\" + s[i];
                ToPrintSubdir(out + "  ");
            }
            else
            {
                System.out.println(out+s[i]);
            }
        }
        directoryName = file.getParent();
    }

    public boolean checkFindSubdir(String name)
    {
        File file = new File(directoryName);
        String[] s = file.list();
        for (int i = 0; i < s.length; i++)
        {
            if (new File(directoryName + "\\" + s[i]).isDirectory())
            {
                if (s[i].equals(name))
                {
                    System.out.println("Существует: " + directoryName + "\\" + s[i]);
                    return true;
                }
                else
                {
                    directoryName = directoryName + "\\" + s[i];
                    if(checkFindSubdir(name))
                    {
                        return true;
                    }
                }
            }
        }
        directoryName = file.getParent();
        return false;

    }


}
