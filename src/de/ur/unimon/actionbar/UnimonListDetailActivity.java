package de.ur.unimon.actionbar;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;

public class UnimonListDetailActivity extends Activity {

	TextView unimonName, unimonHealth, healthText, unimonLevel, skillPoints,
			unimonSpells, chosenUnimonXp, xpText;
	ProgressBar chosenUnimonHealthBar, chosenUnimonXpBar;
	ImageView unimonImage;
	private String chosenUnimonName, chosenUnimonSpells;
	private int chosenUnimonLevel, chosenUnimonSkillPoints,
			unimonImageResource;
	private int maxHealth, health, chosenUnimonCurrentXp,
			chosenUnimonMaxXpPerLevel;
	private Bundle extras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_item_detail);
		extras = this.getIntent().getExtras();
		initUI();
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

		unimonImageResource = extras.getInt("chosen_unimon_image");
		chosenUnimonName = extras.getString("chosen_unimon_name");
		health = extras.getInt("chosen_unimon_health");
		maxHealth = extras.getInt("chosen_unimon_max_health");
		chosenUnimonLevel = extras.getInt("chosen_unimon_level");
		chosenUnimonCurrentXp = extras.getInt("chosen_unimon_current_xp");
		chosenUnimonMaxXpPerLevel = extras
				.getInt("chosen_unimon_max_xp_per_level");
		chosenUnimonSkillPoints = extras.getInt("chosen_unimon_skillPoints");
		chosenUnimonSpells = extras.getString("chosen_unimon_spells");

		unimonName = (TextView) findViewById(R.id.detail_unimon_name);
		unimonHealth = (TextView) findViewById(R.id.detail_unimon_health);
		healthText = (TextView) findViewById(R.id.health_text);

		chosenUnimonHealthBar = (ProgressBar) findViewById(R.id.detail_healthBar);
		unimonLevel = (TextView) findViewById(R.id.detail_unimon_level);
		chosenUnimonXpBar = (ProgressBar) findViewById(R.id.detail_xpBar);
		chosenUnimonXp = (TextView) findViewById(R.id.detail_unimon_xp);
		xpText = (TextView) findViewById(R.id.xp_text);
		skillPoints = (TextView) findViewById(R.id.detail_unimon_skillpoints);
		unimonSpells = (TextView) findViewById(R.id.detail_spell);
		unimonImage = (ImageView) findViewById(R.id.detail_unimon_image);

		unimonImage.setImageResource(unimonImageResource);
		unimonName.setText(chosenUnimonName);
		unimonName.setTypeface(font);
		unimonHealth.setText(health + "/" + maxHealth);
		unimonLevel.setText(getResources().getString(R.string.level_text)
				+ chosenUnimonLevel);
		skillPoints.setText(getResources().getString(R.string.skillpoint_text)
				+ chosenUnimonSkillPoints);
		unimonSpells.setText(chosenUnimonSpells);

		chosenUnimonHealthBar.setMax(maxHealth);
		chosenUnimonHealthBar.setProgress(health);
		double healthPercentage = ((double) health) / maxHealth;
		if (healthPercentage >= 0.75) {
			chosenUnimonHealthBar.setProgressDrawable(getResources()
					.getDrawable(R.drawable.green_progress));
		} else if (healthPercentage <= 0.25) {
			chosenUnimonHealthBar.setProgressDrawable(getResources()
					.getDrawable(R.drawable.red_progress));
			;
		} else
			chosenUnimonHealthBar.setProgressDrawable(getResources()
					.getDrawable(R.drawable.orange_progress));

		chosenUnimonXp.setText(chosenUnimonCurrentXp + "/"
				+ chosenUnimonMaxXpPerLevel);

		chosenUnimonXpBar.setMax(chosenUnimonMaxXpPerLevel);
		chosenUnimonXpBar.setProgress(chosenUnimonCurrentXp);

		chosenUnimonXpBar.setProgressDrawable(getResources().getDrawable(
				R.drawable.purple_progress));
	}

}
