package com.xtu.ThreadTest;
public class BadThreads {

    static String message;

    private static class CorrectorThread
        extends Thread {

        public synchronized void run() {
            try {
            	//System.out.println("runnable");
                sleep(500); 
            } catch (InterruptedException e) {}
            // Key statement 1:
            message = "Mares do eat oats."; 
            //System.out.println("Run() :"+message);
        }
    }

    public static void main(String args[])
        throws InterruptedException {

        (new CorrectorThread()).start();
//        System.out.println("main");
        message = "Mares do not eat oats.";
        Thread.sleep(100);
        // Key statement 2:
        System.out.println(message);
 
       
        }
}