package de.ur.unimon.buildings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class DompteurSkillActivity extends Activity {
	private TextView name, level, skillPoints, health;
	private ImageView unimonImage;
	private ProgressBar healthBar;
	private String skillType;
	private int unimonPosition;
	private Player player;
	private PlayerController playerController;
	private Unimon unimon;
	private ListView spellList;
	private ListAdapter spellListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_skill_activity);
		getExtrasFromIntent();
		getCurrentUnimon();
		initUI();
		initListAdapter();
		initListView();
	}
	
	@Override
	protected void onDestroy() {
		DatabaseController databaseController = new DatabaseController(this);
		databaseController.save();
		super.onDestroy();
	}

	private void initListView() {
		spellList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (unimon.getSkillPoints() > 0) {
					switch (skillType) {
					case "improveSkill":
						unimon.getOwnedSpells().get(position).levelUpSpell();
						skillPoints.setText("Skillpoints: "
								+ unimon.getSkillPoints());
						((BaseAdapter) spellListAdapter).notifyDataSetChanged();
						break;
					case "newSkill":
						unimon.learnSpell(unimon.getNotYetLearnedSpellList()
								.get(position));
						skillPoints.setText("Skillpoints: "
								+ unimon.getSkillPoints());
						((BaseAdapter) spellListAdapter).notifyDataSetChanged();
						break;
					}
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"You dont have any Skillpoints left!",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	}

	private void initListAdapter() {
		spellList = (ListView) findViewById(R.id.dompteur_spell_listView);
		if (skillType.equals("newSkill")) {
			spellListAdapter = new SpellListAdapter(this,
					unimon.getNotYetLearnedSpellList());
		} else if (skillType.equals("improveSkill")) {
			spellListAdapter = new SpellListAdapter(this,
					unimon.getOwnedSpells());
		}
		spellList.setAdapter(spellListAdapter);
	}

	private void getCurrentUnimon() {
		player = playerController.getInstance();
		unimon = player.getUnimonList().get(unimonPosition);
	}

	private void getExtrasFromIntent() {
		Bundle extras = getIntent().getExtras();
		unimonPosition = extras.getInt("unimonPosition");
		skillType = extras.getString("skillType");
	}

	private void initUI() {
		unimonImage = (ImageView) findViewById(R.id.unimon_image);
		name = (TextView) findViewById(R.id.unimon_name);
		level = (TextView) findViewById(R.id.unimon_level);
		skillPoints = (TextView) findViewById(R.id.unimon_skillpoints);
		health = (TextView) findViewById(R.id.unimon_health);
		healthBar = (ProgressBar) findViewById(R.id.healthBar);
		unimonImage.setImageResource(unimon.getImage());
		name.setText(unimon.getName());
		level.setText(getResources().getString(R.string.level_text) + unimon.getLevel());
		skillPoints.setText(getResources().getString(R.string.skillpoint_text) + unimon.getSkillPoints());
		health.setText(unimon.getHealth() + "/" + unimon.getMaxHealth());
		healthBar.setMax(unimon.getMaxHealth());
		healthBar.setProgress(unimon.getHealth());
		double healthPercentage = ((double) unimon.getHealth())
				/ unimon.getMaxHealth();
		if (healthPercentage >= 0.75) {
			healthBar.setProgressDrawable(getResources().getDrawable(
					R.drawable.green_progress));
		} else if (healthPercentage <= 0.25) {
			healthBar.setProgressDrawable(getResources().getDrawable(
					R.drawable.red_progress));
			;
		} else
			healthBar.setProgressDrawable(getResources().getDrawable(
					R.drawable.orange_progress));
	}
}