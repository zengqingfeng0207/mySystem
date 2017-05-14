package com.jx3.Mouse;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Structure;

public class Point  extends  Structure {  
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