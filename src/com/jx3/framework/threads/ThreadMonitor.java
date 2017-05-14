package com.jx3.framework.threads;

import com.jx3.ui.OpenWindow;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
 

public class ThreadMonitor implements Runnable {

	private String windowName;
	
	private char key;
	
	private char charV;

	private int keyInt;
	
	private int intV;
	
	private int sleepValue;
	  
	
	public ThreadMonitor(String windowName,char key,int sleepValue){
		
		super();
		
		this.windowName = windowName;
		
		this.key = key;
		
		this.sleepValue = sleepValue;
		 
			 
	}
	
	public ThreadMonitor(String windowName,int keyInt,int sleepValue){
		
		super();
		
		this.windowName = windowName;
		
		this.keyInt = keyInt;
		
		this.sleepValue = sleepValue;
	}
	
	public void run(){
	
		try {
			
			Thread.sleep(this.sleepValue);
			
			if(charV !=key){

				OpenWindow.keyDow(windowName, key, sleepValue); 
			
			}else if(intV !=keyInt){
			
				OpenWindow.keyDow(windowName, keyInt, sleepValue);
			
			}
			
		} catch (Exception e) {
			
		}
	}
}
