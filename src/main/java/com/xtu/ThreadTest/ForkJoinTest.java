package com.xtu.ThreadTest;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {
	static int dst [] = new int [100000];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int src[] = new int [100000];
		Random random = new Random();
		for(int i = 0;i<src.length;i++)
			src[i] = random.nextInt(256);
		
		BlurTask bt = new BlurTask(src,0,src.length,dst);
		ForkJoinPool pool  = new ForkJoinPool();
		pool.invoke(bt);
		for(int i=0;i<dst.length;i++)
			System.out.println(dst[i]+" ");
}
	}

class BlurTask extends RecursiveAction{
	
	private int [] source;
	private int start;
	private int length;
	private int [] destination;
	
	private int blurWidth = 15;
	
	public BlurTask(int [] source,int start,int length,int [] destination){
		this.source = source;
		this.start = start;
		this.length = length;
		this.destination = destination;
	}
	
	public void computeDirectly(){
		int sidePixels = (blurWidth - 1) / 2;
        for (int index = start; index < start + length; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                                    source.length - 1);
                int pixel = source[mindex];
                rt += (float)((pixel & 0x00ff0000) >> 16)
                      / blurWidth;
                gt += (float)((pixel & 0x0000ff00) >>  8)
                      / blurWidth;
                bt += (float)((pixel & 0x000000ff) >>  0)
                      / blurWidth;
            }
          
            // Reassemble destination pixel.
            int dpixel = (0xff000000     ) |
                   (((int)rt) << 16) |
                   (((int)gt) <<  8) |
                   (((int)bt) <<  0);
            destination[index] = dpixel;
        }
	}

	protected void compute() {
		
		if(length<1000){
			computeDirectly();
			return ;
		}
		int split = length/2;
		invokeAll(new BlurTask(source,start,split,destination),
				  new BlurTask(source,start+split,length-split,destination));
		
	}
	
}

