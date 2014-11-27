package org.solovyev.android.material.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.solovyev.android.material.MaterialColor;
import org.solovyev.android.material.R;

import javax.annotation.Nonnull;

public class Preference extends android.preference.Preference {

	@Nonnull
	private final MaterialColor color = new MaterialColor(R.color.material_text_selector);

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public Preference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		color.init(context, attrs);
	}

	public Preference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		color.init(context, attrs);
	}

	public Preference(Context context, AttributeSet attrs) {
		super(context, attrs);
		color.init(context, attrs);
	}

	public Preference(Context context) {
		super(context);
		color.init(context, null);
	}

	static void fixSummaryTextColor(@Nonnull MaterialColor color, View view) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB && TextUtils.equals(Build.MANUFACTURER, "samsung")) {
			final View summary = view.findViewById(android.R.id.summary);
			if (summary instanceof TextView) {
				final Resources r = view.getResources();
				((TextView) summary).setTextColor(color.getColorList());
			}
		}
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		final View view = super.onCreateView(parent);
		fixSummaryTextColor(color, view);
		return view;
	}
}
