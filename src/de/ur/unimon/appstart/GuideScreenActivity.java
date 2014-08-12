package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;


public class GuideScreenActivity extends Activity{
	
	TextView descriptionTitle, tipsTricksTitle;
	ScrollView description, tipsTricks;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_screen_activity);
		initUI();

	}
	
	private void initUI(){
		descriptionTitle = (TextView) findViewById(R.id.game_description);
		description =(ScrollView) findViewById(R.id.game_description_scroll);
		tipsTricksTitle =(TextView) findViewById(R.id.tips_tricks);
		tipsTricks = (ScrollView) findViewById(R.id.tips_tricks_scroll);		
	}

}