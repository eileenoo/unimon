package de.ur.unimon.start.newgame;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.GuideScreenActivity;
import de.ur.unimon.appstart.OptionsScreenActivity;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewGameActivity extends Activity{
	
	TextView story;
	Button nextStoryPart;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game_activity);
		initUI();

	}

	private void initUI() {
		story = (TextView) findViewById(R.id.story);
		nextStoryPart = (Button) findViewById(R.id.nextStoryPart_button);
	}

}
