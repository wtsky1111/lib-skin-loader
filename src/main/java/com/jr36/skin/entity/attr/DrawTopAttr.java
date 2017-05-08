package com.jr36.skin.entity.attr;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.jr36.skin.entity.SupportAttributes;
import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.entity.attr.base.SkinAttrType;
import com.jr36.skin.loader.SkinManager;

public class DrawTopAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (SupportAttributes.Attr_drawableTop.equals(attrName) && attrTypeName == SkinAttrType.drawable) {
                Drawable[] draws = tv.getCompoundDrawables();
                Drawable drawable = SkinManager.getInstance().getDrawable(attrRefId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                draws[1] = drawable;
                tv.setCompoundDrawables(draws[0], draws[1], draws[2], draws[3]);
            }
        }
    }
}
