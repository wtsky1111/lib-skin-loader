package com.jr36.skin.entity.attr;

import android.view.View;
import android.widget.ImageView;

import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.entity.attr.base.SkinAttrType;
import com.jr36.skin.loader.SkinManager;

public class ImageSrcAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof ImageView) {
            ImageView iv = (ImageView) view;
            if (attrTypeName == SkinAttrType.drawable) {
                iv.setImageDrawable(SkinManager.getInstance().getDrawable(attrRefId));
            }
        }
    }
}
