package com.jr36.skin.loader;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

import com.jr36.skin.config.SkinConfig;
import com.jr36.skin.entity.AttrFactory;
import com.jr36.skin.entity.SkinItem;
import com.jr36.skin.entity.SupportAttributes;
import com.jr36.skin.entity.attr.DynamicAttr;
import com.jr36.skin.entity.attr.base.SkinAttr;
import com.jr36.skin.util.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:21:19
 * <p></p>
 * 自定义的InflaterFactory，用来代替默认的LayoutInflaterFactory
 * 参考链接：http://willowtreeapps.com/blog/app-development-how-to-get-the-right-layoutinflater/
 */
public class SkinInflaterFactoryT implements LayoutInflaterFactory {

    private static String TAG = "SkinInflaterFactory";
    /**
     * 存储那些有皮肤更改需求的View及其对应的属性的集合
     */
    private Map<View, SkinItem> mSkinItemMap = new HashMap<>();
    private AppCompatActivity mAppCompatActivity;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        AppCompatDelegate delegate = mAppCompatActivity.getDelegate();
        View view = delegate.createView(parent, name, context, attrs);

        if (isSkinEnable) {
            if (view == null) {
                view = ViewProducer.createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }
            parseSkinAttr(context, attrs, view);
        }
        return view;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    /**
     * 搜集可更换皮肤的属性
     *
     * @param context
     * @param attrs
     * @param view
     */
    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> viewAttrs = new ArrayList<>();//存储View可更换皮肤属性的集合
        for (int i = 0; i < attrs.getAttributeCount(); i++) {//遍历当前View的属性
            String attrName = attrs.getAttributeName(i);//属性名
            String attrValue = attrs.getAttributeValue(i);//属性值
            if (attrName == null || attrValue == null) continue;
            if ("style".equals(attrName)) {//style theme
                String styleName = attrValue.substring(attrValue.indexOf("/") + 1);
                int styleID = context.getResources().getIdentifier(styleName, "style", context.getPackageName());
                if (styleID == 0) continue;
                TypedArray a = null;
                try {
                    a = context.getTheme().obtainStyledAttributes(styleID, SupportAttributes.skinAttrs);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                if (a == null) continue;
                for (int k = 0; k < SupportAttributes.skinAttrs.length; k++) {
                    String typeName = "";
                    try {
                        typeName = context.getResources().getResourceTypeName(a.getResourceId(k, -1));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                    SkinAttr skinAttr = AttrFactory.get(SupportAttributes.skinAttrs_s[k], a.getResourceId(k, -1), typeName);
                    if (skinAttr != null) viewAttrs.add(skinAttr);
                }
                a.recycle();
            } else if (SupportAttributes.isSupport(attrName) && attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));//资源的id
                    String typeName = "";
                    try {
                        typeName = context.getResources().getResourceTypeName(id);
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                    SkinAttr mSkinAttr = AttrFactory.get(attrName, id, typeName);
                    if (mSkinAttr != null) viewAttrs.add(mSkinAttr);
                } catch (NumberFormatException | Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!ListUtils.isEmpty(viewAttrs)) {
            SkinItem skinItem = new SkinItem();
            skinItem.view = view;
            skinItem.attrs = viewAttrs;
            mSkinItemMap.put(skinItem.view, skinItem);
            if (SkinManager.getInstance().isExternalSkin()) {//如果当前皮肤来自于外部
                skinItem.apply();
            }
        }
    }

    public void applySkin() {
        if (mSkinItemMap.isEmpty()) {
            return;
        }
        for (View view : mSkinItemMap.keySet()) {
            if (view == null) {
                continue;
            }
            mSkinItemMap.get(view).apply();
        }
    }

    /**
     * 清除有皮肤更改需求的View及其对应的属性的集合
     */
    public void clean() {
        if (mSkinItemMap.isEmpty()) {
            return;
        }
        for (View view : mSkinItemMap.keySet()) {
            if (view == null) {
                continue;
            }
            mSkinItemMap.get(view).clean();
        }
    }

    public void addSkinView(SkinItem item) {
        mSkinItemMap.put(item.view, item);
    }

    public void removeSkinView(View view) {
        mSkinItemMap.remove(view);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性
     *
     * @param context
     * @param view
     * @param attrName       属性名
     * @param attrValueResId 属性资源id
     */
    public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId) {
        int id = attrValueResId;
//        String entryName = context.getResources().getResourceEntryName(id);
        String typeName = "";
        try {
            typeName = context.getResources().getResourceTypeName(id);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        SkinAttr mSkinAttr = AttrFactory.get(attrName, id, typeName);
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        List<SkinAttr> viewAttrs = new ArrayList<>();
        viewAttrs.add(mSkinAttr);
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性集合
     *
     * @param context
     * @param view
     * @param pDAttrs
     */
    public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> pDAttrs) {
        List<SkinAttr> viewAttrs = new ArrayList<>();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;

        for (DynamicAttr dAttr : pDAttrs) {
            int id = dAttr.refResId;
//            String entryName = context.getResources().getResourceEntryName(id);
            String typeName = "";
            try {
                typeName = context.getResources().getResourceTypeName(id);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            SkinAttr mSkinAttr = AttrFactory.get(dAttr.attrName, id, typeName);
            if (mSkinAttr != null) viewAttrs.add(mSkinAttr);
        }

        if (ListUtils.isEmpty(viewAttrs)) return;

        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }
}
