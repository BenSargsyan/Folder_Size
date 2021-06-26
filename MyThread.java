import java.io.File;

public class MyThread extends Thread{
    private static volatile long size;

    private long getSize() {
        return size;
    }

    private  void showSize() {
        try {
            System.out.println(this.getSize());
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getFolderSize(File folder) {
        MyThread mt=new MyThread();
        mt.start();
        int tmp=0;
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isFile()) {
                size += files[i].length();
            }
            if (files[i].isDirectory()) {
                size += getFolderSize(files[i]);
            }
            mt.showSize();

        }

        return size;
    }
}
