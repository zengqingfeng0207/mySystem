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
	              // TODO ����־
	              e.printStackTrace();
	          }
	          
	      }
	      
	     
	
	  }

  /**
   * �����Ҫ�߼�
   */
  private void monitor() {
      for (Thread thread :monitoredThread) {
          Thread.State state = thread.getState(); // ���ָ���߳�״̬

          System.out.println(thread.getName() + "����߳�״̬��:"
                  +thread.getState());

          /*
           * �������߳�Ϊ��ֹ״̬,����������߳�
           */
          if (Thread.State.TERMINATED.equals(state) || Thread.State.NEW.equals(state)) {
        	  
              System.out.println(thread.getName()
                      + "����߳��Ѿ���ֹ,���ڿ�ʼ��������߳�!");
              thread.start();
          }else{
        	  
          }
      }
  }

}
