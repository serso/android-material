package org.solovyev.android.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("UnusedDeclaration")
public final class MaterialButton extends Button {

    private final ButtonBase buttonBase = new ButtonBase(this);

    public MaterialButton(Context context) {
        super(context);
        init(context, null);
    }

    public MaterialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(@Nonnull Context context, @Nullable AttributeSet attrs) {
        buttonBase.init(context, attrs);
    }

    public void setColor(@Nonnull ColorStateList color) {
        buttonBase.setColor(color);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setBackground(Drawable background) {
        super.setBackground(buttonBase == null ? background : buttonBase.prepareDrawable(background));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(buttonBase == null ? background : buttonBase.prepareDrawable(background));
    }
}
