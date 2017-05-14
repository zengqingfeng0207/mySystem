package com.jx3.ui;

import java.util.HashMap;
import java.util.Map;









import com.jx3.han.MyUser32;
import com.jx3.han.Rect;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser;

public class MouseClick {

	public  HWND hwnd;
	
	public static User32 user32;
	
	private boolean status;
	
	public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_MOUSEHWHEEL = 526;
    public static final int WM_MOUSEWHEEL = 522;
    public static final int WM_MOUSEMOVE = 512;
	
	private static Map<String,MouseClick>  windowNames = new HashMap<String,MouseClick>();
	
	private static Map<String,LPARAM> lparams = new HashMap<String,LPARAM>();
	
	private static WinDef.WPARAM wParam = new WinDef.WPARAM(0L);
	 
	public MouseClick(String winodwName){
		
		if(hwnd == null){
			
			user32 = User32.INSTANCE;
			
			hwnd = user32.FindWindow(null, winodwName);
			
			
			User32.INSTANCE.SetForegroundWindow(hwnd);
			
			status = true;
		
		}

	}
	
	
	public void mouseEvent(int x,int y ,long sleepValue) throws Exception{
		
		if(hwnd != null && status){
		 
	       
//	        moveClickMouse(hwnd, MouseClick.WM_RBUTTONDOWN, 612, 558);  //��� �Ҽ�
//	      	moveClickMouse(hwnd, MouseClick.WM_RBUTTONUP,  612, 558);
		
		 while (true) {
			 
	        Rect rect = new Rect(); //����λ��
	        user32.ShowWindow(hwnd, 1); //��ô���λ��
	        HWND childHwnd = MyUser32.INSTANCE.WindowFromPoint(rect.getLeft() + x + 10, rect.getTop() + y + 18);
			 
		    //MyUser32.INSTANCE.ChildWindowFromPoint(hwnd, 326, 500); 
			 //MyUser32.INSTANCE.SetWindowText(hwnd, "Frank");//���ô�������
	        int temp1 =  18 << 16 | 10 << 16 >> 16;  

	        WinDef.LPARAM lParam1 = new WinDef.LPARAM(temp1); 
		      
			 String xy = x+":"+y;
			System.out.println(xy);
			 if(lparams.get(xy) == null){
				  
				  int temp = y << 16 | x << 16 >> 16;  
			      
			      WinDef.LPARAM lParam = new WinDef.LPARAM(temp); 
			       
			      moveClickMouse(hwnd, MouseClick.WM_RBUTTONDOWN, lParam); 
			      
			      moveClickMouse(hwnd, MouseClick.WM_RBUTTONUP, lParam); 
			      
			      Thread.sleep(1000L);
			      
			      moveClickMouse(childHwnd, MouseClick.WM_LBUTTONDOWN, lParam1); 
			      
			      moveClickMouse(childHwnd, MouseClick.WM_LBUTTONUP, lParam1);   

			      lparams.put(xy, lParam);
			      
			 }else{
				 
				 WinDef.LPARAM lParam = lparams.get(xy); 
				 
				 moveClickMouse(hwnd, MouseClick.WM_RBUTTONDOWN, lParam); 
			      
			      moveClickMouse(hwnd, MouseClick.WM_RBUTTONUP, lParam); 
			      
			      Thread.sleep(1000L);
			      
				 moveClickMouse(childHwnd, MouseClick.WM_LBUTTONDOWN, lParam1); 
			 
			     moveClickMouse(childHwnd, MouseClick.WM_LBUTTONUP, lParam1);   
				 
			 }
			
			 Thread.sleep(sleepValue);
				
		     mouseEvent(x,y,sleepValue);
		      
		}	
			
	      
	     
			
		}else{
			
			if(hwnd == null){
				
				throw new Exception("���ڲ�����");
			
			}
		}
		
	}
	
	/**
     * ���ƶ��������
     *
     * @param hWnd ���ھ��
     * @param msg  ��Ϣ����
     * @param x    X����
     * @param y    Y����
     */
    public static void moveClickMouse(WinDef.HWND hWnd, int msg, WinDef.LPARAM lParam) {
        
    	

//	      int temp = y << 16 | (x << 16 >> 16); 
//	      
//	      WinDef.LPARAM lParam = new WinDef.LPARAM(temp);
//	      
//        WinDef.UINT_PTR uint = new WinDef.UINT_PTR(msg);
//        User32.MSG msg1 = new User32.MSG();
    	
        user32.PostMessage(hWnd, msg, wParam, lParam);
    }

    
	 
	
    /**
     * 
     * Qingfeng Zeng 2014-10-20 ����10:54:48
     * @param windowName	��������
     * @param x				x����
     * @param y				y����
     * @param sleepValue	��ͣʱ��
     * @throws Exception
     */
    public static void  keyDow(String windowName,Integer x,Integer y,int sleepValue) throws Exception{
     
    	if(windowNames.get(windowName)== null){
    		
    		System.out.println("����"+windowName);
    		
    		MouseClick ow = new MouseClick(windowName);
    		
    		ow.mouseEvent(x,y, sleepValue);
    		
    		windowNames.put(windowName,ow);
    		
    	}else{
    		
    		MouseClick ow =windowNames.get(windowName);
    		
    		ow.mouseEvent(x,y, sleepValue);
    	}
    	
    	
    }

   
}
