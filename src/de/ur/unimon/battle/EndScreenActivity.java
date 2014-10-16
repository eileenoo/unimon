package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.database.DatabaseController;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class EndScreenActivity extends Activity {
	ImageView gameFinished;
	WebView finish_text_one, finish_text_two;
	Button backToMenu;
	DatabaseController controller;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_finished);
		initUI();
		controller = new DatabaseController(this);

		setOnClickListener();
	}

	private void initUI() {

		Typeface font = Typeface
				.createFromAsset(getAssets(), "PokemonFont.ttf");

		gameFinished = (ImageView) findViewById(R.id.game_finish_logo);

		finish_text_one = (WebView) findViewById(R.id.game_finish_text_one);
		finish_text_one.setVerticalScrollBarEnabled(false);
		finish_text_two = (WebView) findViewById(R.id.game_finish_text_two);
		finish_text_two.setVerticalScrollBarEnabled(false);

		String htmlfinishTextOne = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.winner_text_one) + "</p> "
				+ "</body></html>";
		String htmlfinishTextTwo = "<html><body>" + "<p align=\"justify\">"
				+ getString(R.string.winner_text_two) + "</p> "
				+ "</body></html>";

		finish_text_one.loadData(htmlfinishTextOne, "text/html", "utf-8");
		finish_text_two.loadData(htmlfinishTextTwo, "text/html", "utf-8");

		finish_text_one.setBackgroundColor(Color.parseColor("#2A56B2"));
		finish_text_two.setBackgroundColor(Color.parseColor("#2A56B2"));

		WebSettings textOne = finish_text_one.getSettings();
		WebSettings textTwo = finish_text_two.getSettings();

		backToMenu = (Button) findViewById(R.id.back_to_menu);
		backToMenu.setTypeface(font);

	}

	private void setOnClickListener() {
		backToMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				controller.save();
				Intent startscreen = new Intent(EndScreenActivity.this,
						StartScreenActivity.class);
				startActivity(startscreen);
				startscreen.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
	}
}

