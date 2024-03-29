package de.ur.unimon.actionbar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class UnimonListActivity extends Activity {

	Button backToMapButton;
	ListView listUnimons;
	ListAdapter listUnimons_adapter;
	private ArrayList<Unimon> unimons;
	PlayerController playerController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_activity);
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

		backToMapButton = (Button) findViewById(R.id.back_to_map_button_unimons);
		backToMapButton.setTypeface(font);
		backToMapButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent backToMap = new Intent(UnimonListActivity.this,
						MapActivity.class);
				startActivity(backToMap);
				backToMap.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});

		unimons = playerController.getInstance().getUnimonList();
		initListAdapter();
		initListView();

	}

	private void initListView() {
		listUnimons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent unimonDetail = new Intent(UnimonListActivity.this,
						UnimonListDetailActivity.class);
				Unimon chosenUnimon = (Unimon) listUnimons
						.getItemAtPosition(position);

				int chosenUnimonImage = chosenUnimon.getImage();
				String chosenUnimonName = chosenUnimon.getName();
				int chosenUnimonHealth = chosenUnimon.getHealth();
				int chosenUnimonMaxHealth = chosenUnimon.getMaxHealth();
				int chosenUnimonLevel = chosenUnimon.getLevel();
				int chosenUnimonCurrentXp = chosenUnimon.getXp();
				int chosenUnimonMaxXpPerLevel = chosenUnimon.getXpPerLevel();
				int chosenUnimonSkillPoints = chosenUnimon.getSkillPoints();

				String chosenUnimonSpellText = "";
				for (int i = 0; i < unimons.get(position).getOwnedSpells()
						.size(); i++) {
					chosenUnimonSpellText += unimons.get(position)
							.getSpellBySpellNumber(i).getSpellName()
							+ "\n";
				}

				unimonDetail.putExtra("chosen_unimon_image", chosenUnimonImage);
				unimonDetail.putExtra("chosen_unimon_name", chosenUnimonName);
				unimonDetail.putExtra("chosen_unimon_health",
						chosenUnimonHealth);
				unimonDetail.putExtra("chosen_unimon_max_health",
						chosenUnimonMaxHealth);
				unimonDetail.putExtra("chosen_unimon_level", chosenUnimonLevel);
				unimonDetail.putExtra("chosen_unimon_current_xp",
						chosenUnimonCurrentXp);
				unimonDetail.putExtra("chosen_unimon_max_xp_per_level",
						chosenUnimonMaxXpPerLevel);
				unimonDetail.putExtra("chosen_unimon_spells",
						chosenUnimonSpellText);
				unimonDetail.putExtra("chosen_unimon_skillPoints",
						chosenUnimonSkillPoints);
				startActivity(unimonDetail);

			}
		});
	}

	private void initListAdapter() {
		listUnimons = (ListView) findViewById(R.id.listView_unimons);
		listUnimons_adapter = new UnimonListAdapter(this, unimons);
		listUnimons.setAdapter(listUnimons_adapter);
	}

}
