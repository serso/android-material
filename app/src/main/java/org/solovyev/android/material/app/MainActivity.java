package org.solovyev.android.material.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final View preferencesButton = findViewById(R.id.preferences_button);
        preferencesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.preferences_button:
                startActivity(new Intent(getApplicationContext(), PreferencesActivity.class));
                break;
        }
    }
}
