import java.io.File;

public class FolderService {
    private static long size;

    private static void countSize(File f)
    {
        File[] files=f.listFiles();
        for(int i=0;i<files.length;++i)
        {
            if(files[i].isFile())
            {
                size+=files[i].length();
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
                    Thread.sleep(100);
                    System.out.println(size);
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
