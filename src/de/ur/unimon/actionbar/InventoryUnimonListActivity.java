package de.ur.unimon.actionbar;

import de.ur.mi.android.excercises.starter.R;
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
	private ArrayAdapter<String> unimonListInventory_adpater;
	
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
//		unimonListInventory_adpater = new ArrayAdapter<String>();
		unimonListInventory.setAdapter(unimonListInventory_adpater);
	}
	


}
