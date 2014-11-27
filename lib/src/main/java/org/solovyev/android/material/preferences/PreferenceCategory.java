package org.solovyev.android.material.preferences;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import org.solovyev.android.material.R;

public class PreferenceCategory extends android.preference.PreferenceCategory {

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public PreferenceCategory(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PreferenceCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PreferenceCategory(Context context) {
		super(context);
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		final View view = super.onCreateView(parent);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			view.setBackgroundResource(R.drawable.material_preference_category);
		}
		return view;
	}
}
