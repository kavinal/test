package com.xtu.ThreadTest;

import java.util.concurrent.ExecutorService;

public class ThreadPoolAndExecutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 	

	}

}


class Task1 implements Runnable{
	public void run() {
		for(int i = 0;i<10;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(i+" ");
		}
		
	}
}