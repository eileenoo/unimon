
package de.ur.unimon.appstart;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.UnimonList;

public class StartScreenActivity extends Activity {
	
	Button newGame_button;
	Button resume_button;
	Button options_button;
	Button guide_button;
	Context context;
	UnimonList allUnimonsList;
	public static Player player;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}
	
	
	private void initUI(){
    	newGame_button = (Button) findViewById (R.id.newGame_button);
    	resume_button = (Button) findViewById (R.id.resume_button);
    	options_button = (Button) findViewById (R.id.options_button);
    	guide_button = (Button) findViewById (R.id.guide_button);
    	
    	allUnimonsList = new UnimonList();
    	
    	setButtonsOnClick();
    }
    
    private void setButtonsOnClick(){
    	newGame_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			player = new Player();
    			Intent newGame = new Intent (StartScreenActivity.this, NewGameActivity.class);
    			startActivity(newGame);
			}
    	});
    	resume_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
    			createPlayer();
    			Intent resume = new Intent (StartScreenActivity.this, MapActivity.class);
    			startActivity(resume);
			}
    	});
    	options_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
    			Intent options = new Intent (StartScreenActivity.this, OptionsScreenActivity.class);
    			startActivity(options);
			}
    	});
    	guide_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
    			Intent guide = new Intent (StartScreenActivity.this, GuideScreenActivity.class);
    			startActivity(guide);
			}
    	});
    }
    
    
    private void createPlayer() {
		Inventory inventory = new Inventory(4,5,5,4);
		player = new Player(50, allUnimonsList.getUnimonList(), inventory);
	}

}


