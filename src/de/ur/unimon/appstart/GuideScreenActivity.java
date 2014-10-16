package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class GuideScreenActivity extends Activity{
	
	TextView descriptionTitle, tipsTricksTitle;
	WebView story_description, tipsTricks_One, tipsTricks_Two,tipsTricks_Three;
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
		
		story_description = (WebView) findViewById(R.id.story_description);
		story_description.setVerticalScrollBarEnabled(false);
		tipsTricks_One = (WebView) findViewById(R.id.tips_tricks_one);
		tipsTricks_One.setVerticalScrollBarEnabled(false);
		tipsTricks_Two = (WebView) findViewById(R.id.tips_tricks_two);
		tipsTricks_Two.setVerticalScrollBarEnabled(false);
		tipsTricks_Three = (WebView) findViewById(R.id.tips_tricks_three);
		tipsTricks_Three.setVerticalScrollBarEnabled(false);

		String htmlStoryDescription = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.story_description_text) + "</p> " + "</body></html>";
		String htmlTipsTricksOne = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.tips_tricks_one_text) + "</p> " + "</body></html>";
		String htmlTipsTricksTwo = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.tips_tricks_two_text) + "</p> " + "</body></html>";
		String htmlTipsTricksThree = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.tips_tricks_three_text) + "</p> " + "</body></html>";
		
		story_description.loadData(htmlStoryDescription, "text/html","utf-8");
		tipsTricks_One.loadData(htmlTipsTricksOne, "text/html", "utf-8");
		tipsTricks_Two.loadData(htmlTipsTricksTwo, "text/html", "utf-8");
		tipsTricks_Three.loadData(htmlTipsTricksThree, "text/html", "utf-8");
		
		story_description.setBackgroundColor(Color.parseColor("#2A56B2"));
		tipsTricks_One.setBackgroundColor(Color.parseColor("#2A56B2"));
		tipsTricks_Two.setBackgroundColor(Color.parseColor("#2A56B2"));
		tipsTricks_Three.setBackgroundColor(Color.parseColor("#2A56B2"));
		
		descriptionTitle = (TextView) findViewById(R.id.story_description_title);
		tipsTricksTitle =(TextView) findViewById(R.id.tips_tricks);
		
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