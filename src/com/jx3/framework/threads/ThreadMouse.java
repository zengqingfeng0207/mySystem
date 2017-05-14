package com.jx3.framework.threads;

import com.jx3.ui.MouseClick;

public class ThreadMouse implements Runnable {

	private String windowName;
	
	private Integer x;
	
	private Integer y;
 
	private int sleepValue;
	  
	
	public ThreadMouse(String windowName,int x,int y,int sleepValue){
		
		super();
		
		this.windowName = windowName;
		 
		this.x = x;
		
		this.y = y;
		
		this.sleepValue = sleepValue;
		 
			 
	}
	 
	
	public void run(){
	
		try {
			
			Thread.sleep(this.sleepValue);
			
			if(x !=null ){

				MouseClick.keyDow(windowName, x,y, sleepValue); 
			
			}
			
		} catch (Exception e) {
			
		}
	}
}
