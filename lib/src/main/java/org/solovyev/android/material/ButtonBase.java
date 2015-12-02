package org.solovyev.android.material;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class ButtonBase {
    @Nonnull
    private final MaterialColor color = new MaterialColor(R.color.material_button_selector);
    @Nonnull
    private final View view;

    private boolean prepared;

    public ButtonBase(@Nonnull View view) {
        this.view = view;
    }

    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    void init(@Nonnull Context context, @Nullable AttributeSet attrs) {
        color.init(context, attrs);
        updateColor();
    }

    @SuppressWarnings("deprecation")
    private void updateColor() {
        final Drawable oldDrawable = view.getBackground();
        final Drawable newDrawable = prepareDrawable(oldDrawable);
        if (oldDrawable != newDrawable) {
            prepared = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(newDrawable);
            } else {
                view.setBackgroundDrawable(newDrawable);
            }
            prepared = false;
        }
    }

    @Nullable
    Drawable prepareDrawable(@Nullable Drawable drawable) {
        if (drawable != null && !prepared) {
            if (isLollipop()) {
                if (!color.isDefaultColorList()) {
                    drawable.setColorFilter(color.getColorList().getDefaultColor(), PorterDuff.Mode.SRC_IN);
                }
            } else {
                final Resources r = view.getResources();
                final int ih = r.getDimensionPixelSize(R.dimen.button_inset_horizontal);
                final int iv = r.getDimensionPixelSize(R.dimen.button_inset_vertical);
                final StateListDrawable stateDrawable = new StateListDrawable();
                addState(stateDrawable, android.R.attr.state_enabled, android.R.attr.state_pressed);
                addState(stateDrawable, -android.R.attr.state_enabled);
                addState(stateDrawable, android.R.attr.state_enabled);
                return new InsetDrawable(stateDrawable, ih, iv, ih, iv);
            }
        }
        return drawable;
    }

    @SuppressWarnings("deprecation")
    private void addState(@Nonnull StateListDrawable drawable, int... states) {
        final Resources r = view.getResources();
        final int defaultColor = r.getColor(R.color.material_button);
        final int color = this.color.getColorList().getColorForState(states, defaultColor);
        drawable.addState(states, makeButtonShape(color));
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    private Drawable makeButtonShape(int color) {
        final Resources r = view.getResources();
        final Drawable drawable = r.getDrawable(R.drawable.material_button_shape);
        if (drawable instanceof GradientDrawable) {
            drawable.mutate();
            ((GradientDrawable) drawable).setColor(color);
        } else {
            Log.e("Material", "Unable to set accent color for drawable.");
        }
        return drawable;
    }


    public void setColor(@Nonnull ColorStateList color) {
        if (this.color.setColorList(color)) {
            updateColor();
        }
    }
}
