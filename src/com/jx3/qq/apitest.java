package com.jx3.qq;

import com.jx3.han.MyUser32; 
import com.jx3.han.Rect;   
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;   
import com.sun.jna.ptr.IntByReference;
 
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable; 

public class apitest
{
  final User32 user32 = User32.INSTANCE;
  static final int margin = 16; 

  public static void main(String[] args)
  {
    WinDef.HWND hwnd = User32.INSTANCE.FindWindow("TXGuiFoundation", "Frank1");
    if (hwnd == null) {
      System.err.println("未发现 [远程QQ] QQ对话框");
      System.exit(0);
    } 

    SoftKey(hwnd, 'R');
    SoftKey(hwnd, 'E'); SoftKey(hwnd, 'A'); SoftKey(hwnd, 'D'); SoftKey(hwnd, 'Y');
    while (true)
    {
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      Transferable transferable = clipboard.getContents(apitest.class);
      clipboard.setContents(transferable, null);
      GetWindowText(hwnd);

      DataFlavor flavor = DataFlavor.stringFlavor;
      if (transferable.isDataFlavorSupported(flavor))
      {
        try
        {
          String str = (String)transferable.getTransferData(flavor);
         
          System.err.println(str);
          if (str.contains("callme!!!")) {
            ClearQQ(hwnd);
            moveClickMouse(hwnd, 513, 297, 68);
            moveClickMouse(hwnd, 514, 297, 68);
            break;
          }

        }
        catch (Exception ee)
        {
          ee.printStackTrace();
        }
      }
    }
  }

  public static void SoftKey(WinDef.HWND hWnd, char b)
  {
    User32.INSTANCE.PostMessage(hWnd, 256, new WinDef.WPARAM(b), new WinDef.LPARAM(1L));
  }

  public static void GetWindowText(WinDef.HWND hwnd) {
    try {
      Thread.sleep(10000L);
      User32.INSTANCE.ShowWindow(hwnd, 1);
      Rect rect = new Rect();
    //  User32.INSTANCE.GetWindowRect(hwnd, rect);
      moveClickMouse(hwnd, 516, 16, 180);
      moveClickMouse(hwnd, 517, 16, 180);
      Thread.sleep(1000L);
      WinDef.HWND childHwnd = MyUser32.INSTANCE.WindowFromPoint(rect.getLeft() + 16 + 34, rect.getTop() + 180 + 36);

      moveClickMouse(childHwnd, 513, 34, 36);
      moveClickMouse(childHwnd, 514, 34, 36);

      Thread.sleep(1000L);
      moveClickMouse(hwnd, 516, 16, 180);
      moveClickMouse(hwnd, 517, 16, 180);
      Thread.sleep(1000L);
      childHwnd = MyUser32.INSTANCE.WindowFromPoint(rect.getLeft() + 16 + 34, rect.getTop() + 180 + 18);

      moveClickMouse(childHwnd, 513, 34, 18);
      moveClickMouse(childHwnd, 514, 34, 18);
      Thread.sleep(1000L);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  public static void ClearQQ(WinDef.HWND hwnd) {
	  System.out.println("ClearQQ....................");
    try { 
    	Rect rect = new Rect();
      User32.INSTANCE.GetWindowRect(hwnd, rect);
      moveClickMouse(hwnd, 516, 16, 180);
      moveClickMouse(hwnd, 517, 16, 180);
      Thread.sleep(1000L);
      WinDef.HWND childHwnd = MyUser32.INSTANCE.WindowFromPoint(rect.getLeft() + 16 + 34, rect.getTop() + 180 + 158);

      moveClickMouse(childHwnd, 513, 34, 158);
      moveClickMouse(childHwnd, 514, 34, 158);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  public static void moveClickMouse(WinDef.HWND hWnd, int msg, int x, int y)
  {
    WinDef.WPARAM wParam = new WinDef.WPARAM(0L);
    int temp = y << 16 | x << 16 >> 16;
    WinDef.LPARAM lParam = new WinDef.LPARAM(temp); 
    User32.INSTANCE.PostMessage(hWnd, msg, wParam, lParam);
  }

//  public static int readNum(WinDef.HWND windowHwnd)
//  {
//    IntByReference lpdwProcessId = new IntByReference();
//    User32.INSTANCE.GetWindowThreadProcessId(windowHwnd, lpdwProcessId);
// 
//
//    IntByReference lpBuffer = new IntByReference(); 
//
//    return lpBuffer.getValue() + 1;
//  }
}