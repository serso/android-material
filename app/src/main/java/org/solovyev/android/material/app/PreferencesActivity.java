package org.solovyev.android.material.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.solovyev.android.material.preferences.PreferencesFragment;

import javax.annotation.Nonnull;

public class PreferencesActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);
        if (savedInstanceState == null) {
            final FragmentManager fm = getSupportFragmentManager();
            final FragmentTransaction t = fm.beginTransaction();
            t.add(R.id.container, createFragment());
            t.commit();
        }
    }

    @Nonnull
    protected PreferencesFragment createFragment() {
        return PreferencesFragment.create(R.xml.preferences, R.layout.fragment_preferences);
    }
}
