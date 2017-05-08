package com.jr36.skin.entity.attr.base;

import android.view.View;

public abstract class SkinAttr {

    /**
     * name of the attr, ex: background or textSize or textColor
     */
    public String attrName;

    /**
     * id of the attr value refered to, normally is [2130745655]
     */
    public int attrRefId;

    /**
     * entry name of the value , such as [app_exit_btn_background]
     */
    public String attrRefName;

    /**
     * type of the value , such as color or drawable
     */
    public SkinAttrType attrTypeName;

    /**
     * Use to apply view with new TypedValue
     *
     * @param view
     */
    public abstract void apply(View view);

    @Override
    public String toString() {
        return "SkinAttr \n[\nattrName=" + attrName + ", \n"
                + "attrRefId=" + attrRefId + ", \n"
                + "attrRefName=" + attrRefName + ", \n"
                + "attrTypeName=" + attrTypeName
                + "\n]";
    }
}
