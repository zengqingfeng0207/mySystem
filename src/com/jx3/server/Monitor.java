/**
 * 
 */
package com.jx3.server;

import java.util.List;

/**
 * @author qingfengZeng
 *
 */
public class Monitor extends Thread {

	
	public List<Thread> monitoredThread; 
	  
	public Monitor(List<Thread> monitoredThread) {
        this.monitoredThread = monitoredThread;
	}

	  public void run() {
		
		  
	      while (true) {
	    	  
	    	  
	          monitor();
	          
	          try {
	        	  
	        	  Thread.sleep(2000);
	        	  
	        	  
	          } catch (InterruptedException e) {
	              // TODO 记日志
	              e.printStackTrace();
	          }
	          
	      }
	      
	     
	
	  }

  /**
   * 监控主要逻辑
   */
  private void monitor() {
      for (Thread thread :monitoredThread) {
          Thread.State state = thread.getState(); // 获得指定线程状态

          System.out.println(thread.getName() + "监控线程状态是:"
                  +thread.getState());

          /*
           * 如果监控线程为终止状态,则重启监控线程
           */
          if (Thread.State.TERMINATED.equals(state) || Thread.State.NEW.equals(state)) {
        	  
              System.out.println(thread.getName()
                      + "监控线程已经终止,现在开始重启监控线程!");
              thread.start();
          }else{
        	  
          }
      }
  }

}
