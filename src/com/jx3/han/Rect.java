package com.jx3.han;

import com.sun.jna.platform.win32.WinDef;

  
public class Rect extends WinDef.RECT
{
	   public int left;
	    public int top;
	    public int right;
	    public int bottom;
	    
  public int getLeft()
  {
    return this.left;
  }
  public void setLeft(int left) {
    this.left = left;
  }
  public int getRight() {
    return this.right;
  }
  public void setRight(int right) {
    this.right = right;
  }
  public int getTop() {
    return this.top;
  }
  public void setTop(int top) {
    this.top = top;
  }
  public int getButtom() {
    return this.bottom;
  }
  public void setButtom(int bottom) {
    this.bottom = bottom;
  }
  
  
}



