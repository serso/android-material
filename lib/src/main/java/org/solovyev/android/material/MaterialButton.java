package org.solovyev.android.material;

import android.annotation.TargetApi;
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
import android.widget.Button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("UnusedDeclaration")
public final class MaterialButton extends Button {

	private final MaterialColor color = new MaterialColor(R.color.material_button_selector);

	private boolean prepared;

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
		color.init(context, attrs);
		updateColor();
	}

	private void updateColor() {
		final Drawable oldDrawable = getBackground();
		final Drawable newDrawable = prepareDrawable(oldDrawable);
		if (oldDrawable != newDrawable) {
			prepared = true;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				setBackground(newDrawable);
			} else {
				setBackgroundDrawable(newDrawable);
			}
			prepared = false;
		}
	}

	public void setColor(@Nonnull ColorStateList color) {
		if (this.color.setColorList(color)) {
			updateColor();
		}
	}

	@Nullable
	private Drawable prepareDrawable(@Nullable Drawable drawable) {
		if (drawable != null && !prepared && color != null) {
			if (isLollipop()) {
				if (!color.isDefaultColorList()) {
					drawable.setColorFilter(color.getColorList().getDefaultColor(), PorterDuff.Mode.SRC_IN);
				}
			} else {
				final Resources r = getResources();
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

	private static boolean isLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	private void addState(@Nonnull StateListDrawable drawable, int... states) {
		final Resources r = getResources();
		final int defaultColor = r.getColor(R.color.material_button);
		final int color = this.color.getColorList().getColorForState(states, defaultColor);
		drawable.addState(states, makeButtonShape(color));
	}

	@Nonnull
	private Drawable makeButtonShape(int color) {
		final Resources r = getResources();
		final Drawable drawable = r.getDrawable(R.drawable.material_button_shape);
		if (drawable instanceof GradientDrawable) {
			drawable.mutate();
			((GradientDrawable) drawable).setColor(color);
		} else {
			Log.e("Material", "Unable to set accent color for drawable.");
		}
		return drawable;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void setBackground(Drawable background) {
		super.setBackground(prepareDrawable(background));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setBackgroundDrawable(Drawable background) {
		super.setBackgroundDrawable(prepareDrawable(background));
	}
}
