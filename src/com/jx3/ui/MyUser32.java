package com.jx3.ui;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public abstract interface MyUser32 extends StdCallLibrary, WinUser
{
  public static final MyUser32 INSTANCE = (MyUser32)Native.loadLibrary("user32", MyUser32.class, W32APIOptions.DEFAULT_OPTIONS);

  public abstract WinDef.HWND FindWindowEx(WinDef.HWND paramHWND1, WinDef.HWND paramHWND2, WString paramWString1, WString paramWString2);

  public abstract WinDef.HWND WindowFromPoint(int paramInt1, int paramInt2);
  
  public abstract WinDef.HWND ChildWindowFromPoint(WinDef.HWND paramHWND1,int paramInt1, int paramInt2);
  
  public abstract void SetWindowText(WinDef.HWND paramHWND1,String name);
}