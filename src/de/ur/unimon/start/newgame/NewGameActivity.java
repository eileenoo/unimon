package de.ur.unimon.start.newgame;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.GuideScreenActivity;
import de.ur.unimon.appstart.OptionsScreenActivity;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewGameActivity extends Activity{
	
	ImageView wulfmanImg;
	TextView story_part_one;
	Button nextStoryPart;
	int pageNum;
	private static final String TAG = "PAGENUM";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game_activity);
		initUI();

	}

	private void initUI() {
		wulfmanImg = (ImageView) findViewById(R.drawable.ic_launcher);
		story_part_one = (TextView) findViewById(R.id.story_part_one);
		nextStoryPart = (Button) findViewById(R.id.next_button);
		setButtonOnClick();
	}
	
	private void setButtonOnClick() {
		pageNum = 0;
				
		nextStoryPart.setOnClickListener(new OnClickListener(){
			
    		public void onClick(View v) {
    			Log.d(TAG, ""+pageNum);
    			
    			if (pageNum == 0){
    				story_part_one.setText(R.string.story_part_two);
    				pageNum++;
    			}
    			
    			else if (pageNum == 1){
    				story_part_one.setText(R.string.story_part_three);
    				pageNum++;
    			}
    			   			     			
    			else{
    			
    			Intent choose_unimon = new Intent(NewGameActivity.this,
				ChooseUnimonActivity.class);
    			startActivity(choose_unimon);
    			}
			
    		}
		});
		}

}
