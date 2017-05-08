package com.jr36.skin.entity.attr;

import com.jr36.skin.entity.AttrFactory;

public class DynamicAttr {
	
	/**
	 * attr name , defined from {@link AttrFactory} :<br>
	 * should be
	 * <li> AttrFactory.BACKGROUND
	 * <li> AttrFactory.TEXT_COLOR <br>
	 * ...and so on
	 */
	public String attrName;
	
	/**
	 * resource id from default context , eg: "R.drawable.app_bg"
	 */
	public int refResId;
	
	public DynamicAttr(String attrName, int refResId){
		this.attrName = attrName;
		this.refResId = refResId;
	}
}
