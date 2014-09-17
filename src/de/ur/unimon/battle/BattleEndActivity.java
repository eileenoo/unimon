package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.UnimonListAdapter;
import de.ur.unimon.appstart.OptionsScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BattleEndActivity extends Activity{
	
	private TextView moneyEarned, xpEarned, moneyEarnedTextView, xpEarnedTextView, battleLostTextView;
	private Button backToMapButton;
	private boolean isGameWon;
	private int xp;
	private int money;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_end_activity);
		getIntentValues();
		initUI();
	}
	
	@Override
	public void onBackPressed() {
	    //Hardware Zurückbutton disabled
	}

	private void getIntentValues() {
		isGameWon = getIntent().getExtras().getBoolean("IsGameWon");
		Log.d("isGameWon= ", "" + isGameWon);
		xp = getIntent().getExtras().getInt("XP");
		money = getIntent().getExtras().getInt("Money");
	}

	private void initUI() {
		xpEarned = (TextView) findViewById(R.id.xpwon_textview);
		moneyEarned = (TextView) findViewById(R.id.moneywon_textview);
		moneyEarnedTextView = (TextView) findViewById(R.id.battle_end_moneywon_textview);
		xpEarnedTextView = (TextView) findViewById(R.id.battle_end_xpwon_textview);
		battleLostTextView = (TextView) findViewById(R.id.battle_lost_textview);
		
		if (isGameWon) {
			if (OptionsScreenActivity.soundOnButtonChecked == true){
				
			}
			moneyEarned.setText(" " + money);
			xpEarned.setText(" " + xp);
			battleLostTextView.setVisibility(View.GONE);
		} else {
			moneyEarnedTextView.setVisibility(View.GONE);
			xpEarnedTextView.setVisibility(View.GONE);
			xpEarned.setVisibility(View.GONE);
			moneyEarned.setVisibility(View.GONE);
		}

		backToMapButton = (Button) findViewById(R.id.back_to_map_button);
		backToMapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {							
				Intent toMapActivity = new Intent (BattleEndActivity.this, MapActivity.class);
				startActivity(toMapActivity);
				toMapActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
		
	}
}
