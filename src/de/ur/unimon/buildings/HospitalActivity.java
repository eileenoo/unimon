package de.ur.unimon.buildings;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class HospitalActivity extends Activity {

	Button healAllbutton, leaveHospitalButton;
	private ArrayList<Unimon> unimons;
	private Player player;
	private PlayerController playerController;
	private Toast toast;
	ListView listUnimons;
	ListAdapter listUnimons_adpater;
	HospitalLogic hospital;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_activty);
		hospital = new HospitalLogic();
		initUI();
	}
	
	@Override
	public void onBackPressed() {
		
	}

	@Override
	protected void onDestroy() {
		DatabaseController databaseController = new DatabaseController(this);
		databaseController.save();
		super.onDestroy();
	}

	private void initUI() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "PokemonFont.ttf");

		player = playerController.getInstance();
		unimons = player.getUnimonList();
		healAllbutton = (Button) findViewById(R.id.button_healAll);
		healAllbutton.setTypeface(font);

		leaveHospitalButton = (Button) findViewById(R.id.back_to_map_button_hospital);
		leaveHospitalButton.setTypeface(font);
		
		setButtonsOnClick();
		initListAdapter();

	}

	private void setButtonsOnClick() {
		healAllbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				for (int i = 0; i < unimons.size(); i++) {
					if (unimons.get(i).getHealth() != unimons.get(i)
							.getMaxHealth()) {
						hospital.healAll(unimons);
						initListAdapter();
					} else {
						showToast(R.string.hospital_heal_info);
					}
				}
			}
		});
		
		leaveHospitalButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent backToMap = new Intent(HospitalActivity.this,
						MapActivity.class);
				startActivity(backToMap);
				backToMap.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
	}

	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons_adpater = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adpater);
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

}
