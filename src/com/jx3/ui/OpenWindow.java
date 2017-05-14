package com.jx3.ui;


import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;

public class OpenWindow {
	
	public  HWND hwnd;
	
	public  User32 user32;
	
	private boolean status;
	
	private static Map<String,OpenWindow>  windowNames = new HashMap<String,OpenWindow>();
	
	private static Map<String,WPARAM> wparams = new HashMap<String,WPARAM>();
	
	private static LPARAM l= new LPARAM(0);
	 
	public OpenWindow(String winodwName){
		
		if(hwnd == null){
			
			user32 = User32.INSTANCE;
			
			hwnd = user32.FindWindow(null, winodwName);
			
			User32.INSTANCE.SetForegroundWindow(hwnd);
			
			status = true;
		
		}

	}
	
	
	public void postMessage(char wparam,long sleepValue) throws Exception{
		
		if(hwnd != null && status){
			
			if(wparams.get(wparam+"")==null){
				
				WPARAM wparamObject= new WPARAM(wparam);
				
				user32.PostMessage(hwnd, User32.WM_KEYDOWN,wparamObject, l); 
				
				wparams.put(wparam+"",wparamObject);
				
				System.out.println("------------------------"+wparam);
			
			}else{
			
				WPARAM wparamObject=wparams.get(wparam+"");
				
				user32.PostMessage(hwnd, User32.WM_KEYDOWN,wparamObject, l); 
			
			}
			
			Thread.sleep(sleepValue);
			
			postMessage(wparam,sleepValue);
			
		}else{
			
			if(hwnd == null){
				
				throw new Exception("窗口不存在");
			
			}
		}
		
	}
	public void postMessage(int wparamInt,long sleepValue) throws Exception{
			
			if(hwnd != null && status){
				 
				if(wparams.get(wparamInt+"Int")==null){
					
					WPARAM wparamObject= new WPARAM(wparamInt);
					
					if(wparamInt==37){
						
						user32.RegisterHotKey(hwnd, 37, user32.INSTANCE.MOD_WIN, KeyEvent.VK_LEFT_PARENTHESIS);
						
					}else if(wparamInt==38){
						
						user32.RegisterHotKey(hwnd, 38, user32.INSTANCE.MOD_WIN, KeyEvent.VK_UP);
						
					}else if(wparamInt==39){
						
						user32.RegisterHotKey(hwnd, 39, user32.INSTANCE.MOD_WIN, KeyEvent.VK_RIGHT_PARENTHESIS);
						 
					}else if(wparamInt==40){
						
						user32.RegisterHotKey(hwnd, 40, user32.INSTANCE.MOD_WIN, KeyEvent.VK_DOWN);
						
					} 
					
					System.out.println("------------------------"+wparamInt);
					
					user32.PostMessage(hwnd, User32.WM_KEYDOWN,new WPARAM(wparamInt), l);  
					
					wparams.put(wparamInt+"Int",wparamObject);
					
				}else{

					WPARAM wparamObject=wparams.get(wparamInt+"Int");
					
					user32.PostMessage(hwnd, User32.WM_KEYDOWN,wparamObject, l); 
				
				}
				
				
				
				Thread.sleep(sleepValue);
				
				postMessage(wparamInt,sleepValue);
				
				
				
			}else{
				
				if(hwnd == null){
					
					throw new Exception("窗口不存在");
				
				}
			}
			
		}
		
	
    
    public static void  keyDow(String windowName,char key,int sleepValue) throws Exception{
     
    	if(windowNames.get(windowName)== null){
    		
    		System.out.println("创建"+windowName);
    		
    		OpenWindow ow = new OpenWindow(windowName);
    		
    		ow.postMessage(key, sleepValue);
    		
    		windowNames.put(windowName,ow);
    		
    	}else{
    		
    		OpenWindow ow =windowNames.get(windowName);
    		
    		ow.postMessage(key, sleepValue);
    	}
    	
    	
    	
    	
    	
    	
    	
    }

    public static void  keyDow(String windowName,int key,int sleepValue) throws Exception{
    
    	OpenWindow ow = new OpenWindow(windowName);
    
    	ow.postMessage(key, sleepValue);
    
    }
   
}