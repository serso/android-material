package org.solovyev.android.material.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

public class ListActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list);
		final ListView listView = (ListView) findViewById(android.R.id.list);
		final List<Map<String, ?>> rows = new ArrayList<>();
		addRow(rows, "Row #1");
		addRow(rows, "Row #2");
		addRow(rows, "Row #3");
		addRow(rows, "Row #4");
		addRow(rows, "Row #5");
		listView.setAdapter(new SimpleAdapter(this, rows, android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1}) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final View view = super.getView(position, convertView, parent);
				return view;
			}
		});
	}

	private void addRow(@Nonnull List<Map<String, ?>> rows, @Nonnull String title) {
		final Map<String, Object> rowData = new HashMap<>();
		rowData.put("title", title);
		rows.add(rowData);
	}
}
