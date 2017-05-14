package com.jx3.framework.threads;

import java.util.ArrayList;
import java.util.List;

import com.jx3.server.Monitor;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

public class ThreadMonitorTest {
	
		public static final int WM_LBUTTONUP = 514;
	    public static final int WM_LBUTTONDOWN = 513;
	    public static final int WM_RBUTTONUP = 517;
	    public static final int WM_RBUTTONDOWN = 516;
	    public static final int WM_MOUSEHWHEEL = 526;
	    public static final int WM_MOUSEWHEEL = 522;
	    public static final int WM_MOUSEMOVE = 512;
	    public static List<Thread> monitoredThread = new ArrayList<Thread>(); 
	    static HHOOK mouseHHK,keyboardHHK;//��ꡢ���̹��ӵľ��
		  static LowLevelKeyboardProc keyboardHook;//���̹��Ӻ���
		  static HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
			public  static User32  	user32 = User32.INSTANCE;
		  static HWND 	hwnd = user32.FindWindow(null, "Frank1");
		  public static boolean b=false;
	// ��װ����
	    static void setHook() {
	    	 
	        keyboardHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);      
	    }
	    
	  //ж�ع���
	    static void unhook( List<Thread> monitoredThread) {
	    	 if(b){ 
	    		 for(Thread thread :monitoredThread){
	    			 thread.resume();
	    		 }
        	 }else{
        		 for(Thread thread :monitoredThread){ 
        			 thread.suspend(); 
        		 }
        	 }
	    	 b = !b;
	       // User32.INSTANCE.UnhookWindowsHookEx(mouseHHK);     
	    }
	/**
	 * Qingfeng Zeng Aug 31, 2014 3:28:51 PM
	 * @param args
	 */
	public static void main(String[] args) {
		
		  // TODO Auto-generated method stub
  		Runnable thread1 = new ThreadMonitor("Frank1",'Q',1000);//��Rһ��
  		
  		Thread thread2 = new Thread(thread1,"����1"); 
  	
  		
  		
  		Runnable thread3 = new ThreadMonitor("Frank1",37,100);
  		
  		Thread thread4 = new Thread(thread3,"����2"); 
  	
  	
  		Runnable thread5 = new ThreadMonitor("Frank1",'B',1000);//��Bһ��
  		
  		Thread thread6 = new Thread(thread5,"����3"); 
		
  		
  		Runnable runMain = new Monitor(monitoredThread);
		
   	 keyboardHook = new LowLevelKeyboardProc() {
         
         //�ú�����������˼�ο���http://msdn.microsoft.com/en-us/library/windows/desktop/ms644985(v=vs.85).aspx
         public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam) {
             int w = wParam.intValue();
             //����alt��ʱw=.WM_SYSKEYDOWN; ���������󲿷ּ�ʱw=WinUser.WM_KEYDOWN
             if(w==WinUser.WM_KEYDOWN || w==WinUser.WM_SYSKEYDOWN){
                 System.out.println("key down: vkCode = "+lParam.vkCode);
             }
             else if(w==WinUser.WM_KEYUP || w==WinUser.WM_SYSKEYUP){
                 System.out.println("key up: vkCode = "+lParam.vkCode);
              
	             // �������'q'�˳�����'q'��vkCode��81
	             if(lParam.vkCode==81) { 
	            	 unhook(monitoredThread);
	                 System.err.println("program terminated.");
	                // System.exit(0);
	             }
	             if(lParam.vkCode==88) { 
	            	 unhook(monitoredThread);
	                 System.err.println("program terminated.");
	                 System.exit(0);
	             }
             }
             return User32.INSTANCE.CallNextHookEx(keyboardHHK, nCode, wParam, lParam.getPointer());
         }
         
     };
     
     
     setHook();     
	
	
	
	
	

  	
  		
  		
  		
  		Thread threadMain = new Thread(runMain,"������߳�"); 
  		threadMain.start();
  		monitoredThread.add(thread6);
  		monitoredThread.add(thread2);
  		monitoredThread.add(threadMain);
  		monitoredThread.add(thread4);
	
	
  		 int result;
         MSG msg = new MSG();
         // ��Ϣѭ��
         // ʵ����whileѭ��һ�ζ���ִ�У���Щ�����������������ó�����GetMessage����������������Ȼ����ͽ����ˡ�
         while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {          
             if (result == -1) {
                 System.err.println("error in GetMessage");
             //    unhook(monitoredThread);
                 break;
             } else {
                 User32.INSTANCE.TranslateMessage(msg);               
                 User32.INSTANCE.DispatchMessage(msg);
             }
         }
         //unhook(monitoredThread);
	
	
	
	}
	
	
	
	
	

}
