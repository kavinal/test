package com.xtu.ThreadTest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	Thread th = new Thread(new Test1());
    	th.start();
    	while(th.isAlive()){
    		System.out.println("Waiting 1 seconds !!!!!!!");
    	th.join(1000);
    	th.interrupt();
    	th.join(2000);
    	}
    	
    	
//    	long startTime = System.currentTimeMillis();
//    	Thread t = new Thread(new MyRunnable());
//    	t.start();
//    	 System.out.println("Starting Thread!!!!!!!!!!!");
//    		while(t.isAlive()){
//    			System.out.println("Still Waiting!!!!!!!!!");
//    			t.join(1000);
//    			if((System.currentTimeMillis()-startTime > 1000) && t.isAlive()){
//    			
//    				System.out.println("Tired OF Waiting!!");
//    				t.interrupt();
//    				t.join();
//    			}
//    		
//    		}
		 
//        new Thread(new MyRunnable()).run();
//        new MyRunnable().run();
//        new Thread(new MyRunnable()).start();
        
    }
}

 
class Test1 implements Runnable {

	public void run() {
		for(int i =0;i<5;i++){
			try {
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName()+" i= "+i);
				if(Thread.interrupted())
					throw new InterruptedException();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Test1 Was Interrupted!!!!!!!!!!!!");
			}
		}
		
	}
}

class MyRunnable implements Runnable{

	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i<10;i++){
			try{
				System.out.println("MyRunnable Was Starting!!!!");
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName()+"  hello i = "+i);
 				if(Thread.interrupted())
					throw new InterruptedException();
 				System.out.println("I AM NOT FINITHED!!!!!!!!!!!!!!!!!!!!!");
				}
			
		catch(Exception e){
			//e.printStackTrace();
			System.out.println("MyRunnable Was Interrupt");
		}
			
	}
		
	}
	
}
