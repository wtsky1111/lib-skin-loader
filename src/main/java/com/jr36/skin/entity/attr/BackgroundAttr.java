package com.jr36.skin.entity.attr;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.entity.attr.base.SkinAttrType;
import com.jr36.skin.loader.SkinManager;

public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (SkinAttrType.color == attrTypeName) {
            view.setBackgroundColor(SkinManager.getInstance().getColor(attrRefId));
        } else if (SkinAttrType.drawable == attrTypeName) {
            Drawable bg = SkinManager.getInstance().getDrawable(attrRefId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(bg);
            } else {
                view.setBackgroundDrawable(bg);
            }
        }
    }
}
