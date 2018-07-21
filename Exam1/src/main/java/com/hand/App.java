package com.hand;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args)  throws IOException {
        Programme programme = new Programme();
        Thread thread = new Thread(programme);
    }



}




class Programme implements Runnable {
    @Override
    public void run() {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void test()  throws IOException{
        URL url=new URL("http://192.168.11.205:18080/trainning/SampleChapter1.pdf");
        URLConnection connection=url.openConnection();
        InputStream is=connection.getInputStream();
        BufferedInputStream bis=new BufferedInputStream(is);
        File directory = new File("Exam1/tmp");
        if(!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directory.getAbsolutePath() + File.separatorChar + "SampleChapter1.pdf");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos=new BufferedOutputStream(fos);

        byte input[] =new byte[10];
        while (bis.read(input)!=-1){
            bos.write(input);
        }
        bos.flush();
        bos.close();
        fos.close();
        bis.close();
        is.close();
        System.out.println("成功");
    }
}
