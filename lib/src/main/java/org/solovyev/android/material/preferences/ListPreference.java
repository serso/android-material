package org.solovyev.android.material.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import org.solovyev.android.material.MaterialColor;
import org.solovyev.android.material.R;

import javax.annotation.Nonnull;

public class ListPreference extends android.preference.ListPreference {

	@Nonnull
	private final MaterialColor color = new MaterialColor(R.color.material_text_selector);

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		color.init(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		color.init(context, attrs);
	}

	public ListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		color.init(context, attrs);
	}

	public ListPreference(Context context) {
		super(context);
		color.init(context, null);
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		final View view = super.onCreateView(parent);
		Preference.fixSummaryTextColor(color, view);
		return view;
	}
}
