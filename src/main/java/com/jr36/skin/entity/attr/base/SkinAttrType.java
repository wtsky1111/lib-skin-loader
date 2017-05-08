package com.jr36.skin.entity.attr.base;

/**
 * Created by wentao *_*
 * on 2017/1/12
 */

public enum SkinAttrType {
    color, drawable, no_definition;

    public static SkinAttrType type(String attrType) {
        if ("color".equals(attrType)) {
            return color;
        }
        if ("drawable".equals(attrType)) {
            return drawable;
        }
        return no_definition;
    }
}
