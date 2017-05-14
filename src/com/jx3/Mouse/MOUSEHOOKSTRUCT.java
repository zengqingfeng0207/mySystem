package com.jx3.Mouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.POINT;

public class MOUSEHOOKSTRUCT extends Structure {
	public class ByReference extends MOUSEHOOKSTRUCT implements
	    Structure.ByReference {
	};
	
	public POINT pt;
	public HWND hwnd;
	public int wHitTestCode;
	public ULONG_PTR dwExtraInfo;
	 
    
	public List getFieldOrder() {
	    ArrayList fields = new ArrayList();
	    fields.add("pt");
	    fields.add("hwnd");
	    fields.add("wHitTestCode");
	    fields.add("dwExtraInfo");
	    return fields;
	}
	 
}