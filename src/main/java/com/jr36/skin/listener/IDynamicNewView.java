package com.jr36.skin.listener;

import java.util.List;

import android.view.View;
import com.jr36.skin.entity.attr.DynamicAttr;

public interface IDynamicNewView {
	void dynamicAddView(View view, List<DynamicAttr> pDAttrs);
}
