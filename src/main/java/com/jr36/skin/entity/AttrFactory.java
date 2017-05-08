package com.jr36.skin.entity;


import com.jr36.skin.entity.attr.BackgroundAttr;
import com.jr36.skin.entity.attr.DrawTopAttr;
import com.jr36.skin.entity.attr.ImageSrcAttr;
import com.jr36.skin.entity.attr.TextColorAttr;
import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.entity.attr.base.SkinAttrType;

public class AttrFactory {
    public static SkinAttr get(String attrName, int attrValueRefId, String attrTypeName) {

        SkinAttr mSkinAttr;

        if (SupportAttributes.Attr_background.equals(attrName)) {
            mSkinAttr = new BackgroundAttr();
        } else if (SupportAttributes.Attr_textColor.equals(attrName)) {
            mSkinAttr = new TextColorAttr();
        } else if (SupportAttributes.Attr_drawableTop.equals(attrName)) {
            mSkinAttr = new DrawTopAttr();
        } else if (SupportAttributes.Attr_src.equals(attrName)) {
            mSkinAttr = new ImageSrcAttr();
        } else {
            return null;
        }

        mSkinAttr.attrName = attrName;
        mSkinAttr.attrRefId = attrValueRefId;
        mSkinAttr.attrTypeName = SkinAttrType.type(attrTypeName);
        return mSkinAttr;
    }

    public static SkinAttr get(String attrName, int attrValueRefId) {
        return get(attrName,attrValueRefId,"");
    }
}
