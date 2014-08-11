package de.ur.unimon.actionbar;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UnimonListActivity extends Activity{
	
	ListView listUnimons;
	ListAdapter listUnimons_adpater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_activity);
		initUI();

	}

	private void initUI() {
		initListView();
		initListAdapter();
		
	}
	
	
	private void initListView() {
		listUnimons = (ListView) findViewById(R.id.listView_inventory);
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent unimon_detail = new Intent(UnimonListActivity.this,
						UnimonListDetailActivity.class);
				startActivity(unimon_detail);
				
				}
		});
	}
	
	
	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_inventory);
		//		listUnimons_adpater = new ArrayAdapter<String>();
		listUnimons.setAdapter(listUnimons_adpater);
	}
	

}
