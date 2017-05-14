package com.jx3.han;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public abstract interface MyUser32 extends StdCallLibrary, WinUser
{
  public static final MyUser32 INSTANCE = (MyUser32)Native.loadLibrary("user32", MyUser32.class, W32APIOptions.DEFAULT_OPTIONS);

  public abstract WinDef.HWND FindWindowEx(WinDef.HWND paramHWND1, WinDef.HWND paramHWND2, WString paramWString1, WString paramWString2);

  public abstract WinDef.HWND WindowFromPoint(int paramInt1, int paramInt2);
  

  public static class RECT extends Structure
  {
    public int left;
    public int top;
    public int right;
    public int bottom;
    private List fieldOrder;
    public Rectangle toRectangle()
    {
      return new Rectangle(this.left, this.top, this.right - this.left, this.bottom - this.top);
    }

    public String toString() {
      return "[(" + this.left + "," + this.top + ")(" + this.right + "," + this.bottom + ")]";
    }

	@Override
	protected List getFieldOrder() {

	    synchronized (this) {
	      if (this.fieldOrder == null) {
	        this.fieldOrder = new ArrayList();
	      }
	      return this.fieldOrder;
	    }
	}
  }
}

