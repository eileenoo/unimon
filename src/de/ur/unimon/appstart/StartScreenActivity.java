
package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;

import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.start.newgame.NewGameActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreenActivity extends Activity {
	
	Button newGame_button;
	Button resume_button;
	Button options_button;
	Button guide_button;
	Context context;

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
    	setButtonsOnClick();
    }
    
    private void setButtonsOnClick(){
    	newGame_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
    			Intent newGame = new Intent (StartScreenActivity.this, NewGameActivity.class);
    			startActivity(newGame);
			}
    	});
    	resume_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
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

}


