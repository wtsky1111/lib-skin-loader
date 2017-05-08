package com.jr36.skin.entity;

import java.util.ArrayList;
import java.util.List;

import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.util.ListUtils;

import android.view.View;

public class SkinItem {
	
	public View view;
	
	public List<SkinAttr> attrs;
	
	public SkinItem(){
		attrs = new ArrayList<>();
	}
	
	public void apply(){
		if(ListUtils.isEmpty(attrs)){
			return;
		}
		for(SkinAttr at : attrs){
			at.apply(view);
		}
	}
	
	public void clean(){
		if(ListUtils.isEmpty(attrs)){
			return;
		}
		for(SkinAttr at : attrs){
			at = null;
		}
	}

	@Override
	public String toString() {
		return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
	}
}
