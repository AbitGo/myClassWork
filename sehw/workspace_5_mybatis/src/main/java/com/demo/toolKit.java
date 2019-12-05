package com.demo;

import org.junit.runner.RunWith;

import java.io.*;



public class toolKit {
    public static void readFromTextFile(String pathname) throws IOException{
        pathname = "src/main/resources/movies.dat";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        int count = 0;
        while(line != null) {
            //
            count++;
            System.out.println(line);
            line = br.readLine();
        }
        System.out.println("it is ok and count is:"+count);
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        readFromTextFile("");
    }

}
