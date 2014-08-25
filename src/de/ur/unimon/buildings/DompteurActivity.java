package de.ur.unimon.buildings;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.startgame_logic.Player;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DompteurActivity extends Activity{
	Player player;
	Button newSkill, improveSkill;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_activity);
		initUI();
}

	private void initUI() {
		newSkill = (Button) findViewById(R.id.dompteur_new_skill);
		improveSkill = (Button) findViewById(R.id.dompteur_improve_skill);
		player = de.ur.unimon.appstart.StartScreenActivity.player;
		setButtonsOnClick();
	}

	private void setButtonsOnClick() {
		newSkill.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {	
//    			DompteurLogic.learnNewSkill(player.ownUnimonList, 0);
    			
    			
			}
		});
		
	}
}