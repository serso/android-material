package org.solovyev.android.material;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class MaterialColor {

    private final int defaultColorListResId;
    private ColorStateList colorList;
    private boolean defaultColorList;

    public MaterialColor(int defaultColorListResId) {
        this.defaultColorListResId = defaultColorListResId;
    }

    public void init(@Nonnull Context context, @Nullable AttributeSet attrs) {
        final Resources resources = context.getResources();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Material);
            if (a.hasValue(R.styleable.Material_materialColor)) {
                colorList = a.getColorStateList(R.styleable.Material_materialColor);
            } else {
                defaultColorList = true;
                colorList = resources.getColorStateList(defaultColorListResId);
            }

            a.recycle();
        } else {
            this.defaultColorList = true;
            this.colorList = resources.getColorStateList(defaultColorListResId);
        }
    }

    @Nonnull
    public ColorStateList getColorList() {
        return colorList;
    }

    public boolean setColorList(@Nonnull ColorStateList colorList) {
        if (!this.colorList.equals(colorList)) {
            this.colorList = colorList;
            this.defaultColorList = false;
            return true;
        }

        return false;
    }

    public boolean isDefaultColorList() {
        return defaultColorList;
    }
}
