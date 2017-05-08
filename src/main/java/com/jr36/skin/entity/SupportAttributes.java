package com.jr36.skin.entity;

/**
 * Created by wentao *_*
 * on 2017/1/12
 */

public class SupportAttributes {

    public static final String Attr_textColor = "textColor";

    public static final String Attr_background = "background";

    public static final String Attr_drawableTop = "drawableTop";

    public static final String Attr_src = "src";

    public static int[] skinAttrs = new int[]{android.R.attr.textColor, android.R.attr.background
            , android.R.attr.drawableTop, android.R.attr.src};

    public static String[] skinAttrs_s = new String[]{"textColor", "background", "drawableTop", "src"};

    public static boolean isSupport(String attrName) {
        return Attr_textColor.equals(attrName) || Attr_background.equals(attrName) ||
                Attr_drawableTop.equals(attrName) || Attr_src.equals(attrName);
    }
}
