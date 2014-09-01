package de.ur.unimon.actionbar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class InventoryUnimonListActivity extends Activity {

	ListView unimonListInventory;
	private UnimonListAdapter unimonListInventory_adpater;
	private ArrayList<Unimon> unimons;
	Button healpot, revive, protector;
	PlayerController playerController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_unimon_list_activity);
		initUI();

	}

	private void initUI() {
		unimons = playerController.getInstance().getUnimonList();
		initListAdapter();
		initListView();

	}

	private void initListView() {
		unimonListInventory = (ListView) findViewById(R.id.listView_unimons);
		healpot = (Button) findViewById(R.id.healpot);
		revive = (Button) findViewById(R.id.revive);
		protector = (Button) findViewById(R.id.protector);
		initListAdapter();

	}

	private void initListAdapter() {
		unimonListInventory = (ListView) findViewById(R.id.listView_unimons);
		unimonListInventory_adpater = new UnimonListAdapter(this, unimons);
		unimonListInventory.setAdapter(unimonListInventory_adpater);
	}

}
