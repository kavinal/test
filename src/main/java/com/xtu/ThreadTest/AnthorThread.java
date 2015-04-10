package com.xtu.ThreadTest;

import java.util.Random;

public class AnthorThread {

	public static void main(String[] args) {
		Drop drop = new Drop();
		new Thread(new Producter(drop)).start();
		new Thread(new Consummer(drop)).start();

	}

}

class Drop{
	
	private String message;
	private boolean empty = true;
	
	public synchronized String take(){
		while(empty){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			empty = true;
			
			notifyAll();
			 
		}
		
		return message;
	}
	
	public synchronized void put(String str){
		while(!empty){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		message = str;
		empty=false;
		notifyAll();
	}	
}

class Producter implements Runnable{

	private Drop drop;
	public Producter(Drop drop){
		this.drop = drop;
	}
	public void run() {
		Random random = new Random();
		String [] info = {
					  "Mares eat oats",
			          "Does eat oats",
			          "Little lambs eat ivy",
			          "A kid will eat ivy too"	
		};
		
		for(int i = 0;i<info.length;i++){
			drop.put(info[i]);
		try {
			Thread.sleep(random.nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		drop.put("DONE");
		
	}
	
}

class Consummer implements Runnable{
	private Drop drop;
	public Consummer(Drop drop){
		this.drop = drop;
	}
	
	public void run() {
		Random random = new Random();
		for(String info = drop.take();!info.equals("DONE");info=drop.take()){
			System.out.println("MESSAGE REVIEVED: "+info);
		try {
			Thread.sleep(random.nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
	
}
