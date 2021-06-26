import java.io.File;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        //Directory example to copy -- C:\Program Files (x86)
        Scanner sc=new Scanner(System.in);
        System.out.println("Pleas enter file directory");
        String path=sc.next();
        File folder=new File(path);
        MyThread m=new MyThread();
        m.start();
        m.getFolderSize(folder);
    }
}
