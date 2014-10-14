package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class GuideScreenActivity extends Activity{
	
	TextView descriptionTitle, tipsTricksTitle, tipsTricksOne, tipsTricksTwo, tipsTricksThree;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_screen_activity);
		initUI();

	}
	
	private void initUI(){
		descriptionTitle = (TextView) findViewById(R.id.story_description);
		tipsTricksTitle =(TextView) findViewById(R.id.tips_tricks);
		tipsTricksOne = (TextView) findViewById(R.id.tips_tricks_one);
		tipsTricksTwo = (TextView) findViewById(R.id.tips_tricks_two);
		tipsTricksThree = (TextView) findViewById(R.id.tips_tricks_three);
		
	}

}