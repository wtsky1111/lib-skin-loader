package com.jr36.skin.entity.attr;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.jr36.skin.entity.SupportAttributes;
import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.loader.SkinManager;

public class TextColorAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (SupportAttributes.Attr_textColor.equals(attrName)) {
                tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrRefId));
            }
        }
    }
}
