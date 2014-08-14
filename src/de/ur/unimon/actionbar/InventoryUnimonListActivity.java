package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class InventoryUnimonListActivity extends Activity{
	
	ListView unimonListInventory;
	private UnimonListAdapter unimonListInventory_adpater;
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
		unimonListInventory = (ListView) findViewById(R.id.listView_unimons);
		unimonListInventory.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				Intent unimon_inventory = new Intent(InventoryUnimonListActivity.this,
						InventoryActivity.class);
				startActivity(unimon_inventory);
				
				}
		});
	}
	
	
	private void initListAdapter() {
		unimonListInventory = (ListView) findViewById(R.id.listView_unimons);
		unimonListInventory_adpater = new UnimonListAdapter(this, unimons);
		unimonListInventory.setAdapter(unimonListInventory_adpater);
	}
	


}
