package de.ur.unimon.actionbar;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.mapoverview.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InventoryActivity extends Activity{
	
	TextView money;
	TextView xp;
	ListView listInventory;
	private ArrayAdapter<String> listInventory_adpater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_activity);
		initUI();

	}

	private void initUI() {
		money = (TextView) findViewById(R.id.money);
		xp = (TextView) findViewById(R.id.xp);
		
		initListView();
		initListAdapter();
		
	}
	
	
	private void initListView() {
		listInventory = (ListView) findViewById(R.id.listView_inventory);
		listInventory.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent inventory_item = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(inventory_item);
				
				}
		});
	}
	
	
	private void initListAdapter() {
		listInventory = (ListView) findViewById(R.id.listView_inventory);
//		listInventory_adpater = new ArrayAdapter<String>();
		listInventory.setAdapter(listInventory_adpater);
	}
	

}
