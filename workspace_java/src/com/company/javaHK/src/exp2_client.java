package com.company.javaHK.src;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class exp2_client {

    public static void main(String[] args) throws IOException {
        Socket soc = new Socket("localhost", 10006);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine()+"\n";
        System.out.println("exp2_client start to send int to server" + "data is" + message);
        String temp;
        PrintStream ps = new PrintStream(soc.getOutputStream());
        ps.print(message);

        //¶ÁÈ¡Êý¾Ý
        BufferedReader binBufferedReader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        do {
            temp = binBufferedReader.readLine();
            if (temp == null) {
                break;
            }
            System.out.println("return data is: " + temp);
        } while (true);

        ps.close();
        soc.close();
    }

}
