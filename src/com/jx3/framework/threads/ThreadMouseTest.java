package com.jx3.framework.threads;

public class ThreadMouseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Runnable thread1 = new ThreadMouse("1.png - 画图",500,680,1000);
		Runnable thread3 = new ThreadMouse("剑侠情缘网络版叁 - 蝶恋花 @ 电信四区",1500,1500,100);
	//	Thread thread2 = new Thread(thread1,"按键1"); 
		Thread thread4 = new Thread(thread3,"按键2"); 
	//	thread2.start();
		thread4.start();
//		Runnable thread3 = new ThreadMouse("Frank2",326,500,1000);
//		
//		Thread thread4 = new Thread(thread3,"按键2"); 
//	
//		thread4.start();
	}	
}
