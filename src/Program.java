import java.util.Scanner;

public class Program
{
    public static void main(String[] args) {
        WorkingDirectory dir = WorkingDirectory.getInstance("C:\\Users\\Pavel\\OneDrive - vyatsu\\5 семестр\\Java\\Lab 2");
        int choice;
        do {
            System.out.println("1) Напечатать содержимое\n2) Имя родительской папки\n3) Перейти в родительскую папку\n" +
                    "4) Перейти в подкаталог\n5) Проверить существование дочерней папки\n" +
                    "6) Удалить подкаталоги в текущей папке\n7) Создать подкаталог\n" +
                    "8) Найти файлы с указанным расширением\n9) Иерархический список\n" +
                    "10) Полный путь\n11) Поиск заданного каталога");
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    dir.ToPrint();
                    break;
                case 2:
                    dir.ToParentName();
                    break;
                case 3:
                    dir.ToParent();
                    dir.ToPrintPath();
                    break;
                case 4:
                    System.out.print("Название подкаталога:");
                    String name = new Scanner(System.in).nextLine();
                    dir.ToChildDir(name);
                    dir.ToPrintPath();
                    break;
                case 5:
                    System.out.print("Название подкаталога:");
                    name = new Scanner(System.in).nextLine();
                    if (dir.checkChildDir(name)) {
                        System.out.println("Существует");
                    } else {
                        System.out.println("Не существует");
                    }
                    break;
                case 6:
                    dir.DeleteAllDir();
                    break;
                case 7:
                    System.out.print("Название подкаталога:");
                    name = new Scanner(System.in).nextLine();
                    dir.ToCreateSubdir(name);
                    break;
                case 8:
                    System.out.print("Название расширения:");
                    name = new Scanner(System.in).nextLine();
                    dir.ToFindEx(name);
                    break;
                case 9:
                    dir.ToPrintSubdir(" ");
                    break;
                case 10:
                    dir.ToPrintPath();
                    break;
                case 11:
                    System.out.print("Название подкаталога:");
                    name = new Scanner(System.in).nextLine();
                    if(dir.checkFindSubdir(name))
                    {
                        System.out.println("Нашлось");
                    }
                    else
                    {
                        System.out.println("Не нашлось");
                    }
                    break;
            }
        }
        while(choice!=0);
    }
}
