package de.ur.unimon.actionbar;

import java.util.ArrayList;


import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;
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
	private ArrayList <Unimon> unimons = new ArrayList <Unimon>();
	
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
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent unimonDetail = new Intent(UnimonListActivity.this,
						UnimonListDetailActivity.class);
				Unimon chosenUnimon = (Unimon) listUnimons.getItemAtPosition(position);
				String chosenUnimonName = chosenUnimon.getName();
			//	Image chosenUnimmonImage = chosenUnimon.getImage();
				unimonDetail.putExtra("chosen_unimon_name", chosenUnimonName);
			//	unimonDetail.putExtra("chosen_unimon_image", chosenUnimonImage);
				startActivity(unimonDetail);								
								
				}
		});
	}
	
	
	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons_adpater = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adpater);
	}
	

}
