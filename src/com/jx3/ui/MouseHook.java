package com.jx3.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jna.Structure;
import com.sun.jna.platform.unix.X11.Font;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.MSG;

public class MouseHook extends JFrame {  
    private static final long serialVersionUID = 1L;  
    public static final int WM_MOUSEMOVE = 512;  
    public static final int WM_LBUTTONDOWN = 513;  
    public static final int WM_LBUTTONUP = 514;  
    public static final int WM_RBUTTONDOWN = 516;  
    public static final int WM_RBUTTONUP = 517;  
    public static final int WM_MBUTTONDOWN = 519;  
    public static final int WM_MBUTTONUP = 520;  
    private static HHOOK hhk;  
    private static LowLevelMouseProc mouseHook;  
  
    private JLabel labelX;  
    private JLabel labelY;  
    private JPanel contentPane;  
  
    public static void main(String[] args) {  
        final MouseHook frame = new MouseHook();  
        frame.setVisible(true);  
  
        final User32 lib = User32.INSTANCE;  
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);  
        mouseHook = new LowLevelMouseProc() {  
            public LRESULT callback(int nCode, WPARAM wParam,  
                    MOUSEHOOKSTRUCT info) {  
                if (nCode >= 0) {  
                    switch (wParam.intValue()) {  
                    case MouseHook.WM_MOUSEMOVE:  
                        System.err.println("in callback,  x=" + info.pt.x  
                                + " y=" + info.pt.y);  
                        frame.getLabelX().setText(String.valueOf(info.pt.x));  
                        frame.getLabelY().setText(String.valueOf(info.pt.y));  
                    }  
                }  
                return lib  
                        .CallNextHookEx(hhk, nCode, wParam, info.getPointer());  
            }  
        };  
        hhk = lib.SetWindowsHookEx(User32.WH_MOUSE_LL, mouseHook, hMod, 0);  
  
        int result;  
        MSG msg = new MSG();  
        while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {  
            if (result == -1) {  
                System.err.println("error in get message");  
                break;  
            } else {  
                System.err.println("got message");  
                lib.TranslateMessage(msg);  
                lib.DispatchMessage(msg);  
            }  
        }  
        lib.UnhookWindowsHookEx(hhk);  
    }  
  
    public MouseHook() {  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setBounds(100, 100, 450, 300);  
        contentPane = new JPanel();  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
        setContentPane(contentPane);  
        contentPane.setLayout(null);  
  
        JLabel lblX = new JLabel("X:");  
        lblX.setFont(new  java.awt.Font("ËÎÌו",java.awt.Font.PLAIN, 14));  
        lblX.setBounds(46, 33, 22, 27);  
        contentPane.add(lblX);  
  
        JLabel lblY = new JLabel("Y:"); 
        lblY.setFont(new  java.awt.Font("ËÎÌו",java.awt.Font.PLAIN, 14));
        lblY.setBounds(144, 39, 17, 15);  
        contentPane.add(lblY);  
  
        labelX = new JLabel("");  
        labelX.setBounds(65, 39, 54, 15);  
        contentPane.add(labelX);  
  
        labelY = new JLabel("");  
        labelY.setBounds(160, 39, 54, 15);  
        contentPane.add(labelY);  
    }  
  
    public JLabel getLabelX() {  
        return labelX;  
    }  
  
    public void setLabelX(JLabel labelX) {  
        this.labelX = labelX;  
    }  
  
    public JLabel getLabelY() {  
        return labelY;  
    }  
  
    public void setLabelY(JLabel labelY) {  
        this.labelY = labelY;  
    }  
  
    public interface LowLevelMouseProc extends HOOKPROC {  
        LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);  
    }  
  
    public class Point extends Structure {  
        public class ByReference extends Point implements Structure.ByReference {  
        };  
        public com.sun.jna.NativeLong x;  
        public com.sun.jna.NativeLong y;
		@Override
		protected List getFieldOrder() {
			 List a = new ArrayList();  
			 a.add("x");
			 a.add("y");
			return a;
		}  
    }  
  
    public static class MOUSEHOOKSTRUCT extends Structure {  
        public static class ByReference extends MOUSEHOOKSTRUCT implements  
                Structure.ByReference {  
        };  
  
        public User32.POINT pt;  
        public User32.HWND hwnd;  
        public int wHitTestCode;  
        public ULONG_PTR dwExtraInfo;
		@Override
		protected List getFieldOrder() {
			List a = new ArrayList();  
			 a.add("pt");
			 a.add("hwnd");
			 a.add("wHitTestCode");
			 a.add("dwExtraInfo");
			return a;
		}  
        
    }  
} 