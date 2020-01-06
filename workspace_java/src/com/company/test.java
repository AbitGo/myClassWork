package com.company;


public class test extends Thread implements Runnable {
    @Override
    public void run() {
        System.out.println("this is run()");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new test());
        t.start();
    }
}
