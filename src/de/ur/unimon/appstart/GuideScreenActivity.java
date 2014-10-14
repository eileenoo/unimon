package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class GuideScreenActivity extends Activity{
	
	TextView descriptionTitle, descriptionText, tipsTricksTitle, tipsTricksOne, tipsTricksTwo, tipsTricksThree;
	Button backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_screen_activity);
		initUI();

	}
	
	@Override
	public void onBackPressed() {
	}
	
	private void initUI(){
		Typeface font = Typeface.createFromAsset(getAssets(), "PokemonFont.ttf");
		
		descriptionTitle = (TextView) findViewById(R.id.story_description_title);
		descriptionText = (TextView) findViewById(R.id.story_description);
		tipsTricksTitle =(TextView) findViewById(R.id.tips_tricks);
		tipsTricksOne = (TextView) findViewById(R.id.tips_tricks_one);
		tipsTricksTwo = (TextView) findViewById(R.id.tips_tricks_two);
		tipsTricksThree = (TextView) findViewById(R.id.tips_tricks_three);
		
		backButton = (Button) findViewById(R.id.back_button);
		
		descriptionTitle.setTypeface(font);
		tipsTricksTitle.setTypeface(font);
		backButton.setTypeface(font);
		
		setButtonOnClick();
		
	}

	private void setButtonOnClick() {
		backButton.setOnClickListener(new OnClickListener(){
			public void onClick (View v){
				Intent backToMenu = new Intent (GuideScreenActivity.this, StartScreenActivity.class);
				backToMenu.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(backToMenu);
			}
		});
		
	}

}