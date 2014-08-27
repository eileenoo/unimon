package de.ur.unimon.buildings;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.startgame_logic.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DompteurUnimonListActivity extends Activity{
	Player player;
	Button newSkill, improveSkill;
	TextView unimonInfo;
	private String chosenUnimonName;
	private Bundle extras;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_unimon_list_activity);
		extras = this.getIntent().getExtras();
		player = de.ur.unimon.appstart.StartScreenActivity.player;
		initUI();
}

	private void initUI() {
		newSkill = (Button) findViewById(R.id.dompteur_new_skill);
		improveSkill = (Button) findViewById(R.id.dompteur_improve_skill);	
		unimonInfo = (TextView) findViewById(R.id.unimon_name);
		chosenUnimonName = extras.getString("chosen_unimon_name");
		unimonInfo.setText(chosenUnimonName);
		setButtonsOnClick();
	}

	private void setButtonsOnClick() {
		newSkill.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {	
    			//DompteurLogic.learnNewSkill(player.ownUnimonList, 0);
    			
    			
			}
		});
		
	}
}
