import java.io.File;

public class FolderService {
    private volatile static  long size;

    private static void countSize(File f)
    {
        long tmp=0;
        File[] files=f.listFiles();
        for(int i=0;i<files.length;++i)
        {
            if(files[i].isFile())
            {
                tmp= files[i].length();
                tmp=tmp/1048576;
                size+=tmp;
            }
            else
            {
              countSize(files[i]);
            }
        }
    }

    public static void printSize(File f)
    {
        Thread getSize=new Thread(() -> countSize(f));

        Thread sizeOutput=new Thread(() -> {
            while(getSize.isAlive())
            {
                try {
                    Thread.sleep(1);
                    System.out.println(size + "MB");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        getSize.start();
        sizeOutput.start();
    }
}
