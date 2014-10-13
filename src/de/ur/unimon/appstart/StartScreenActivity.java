package de.ur.unimon.appstart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.trainer.TrainerListController;
import de.ur.unimon.unimons.UnimonList;

public class StartScreenActivity extends Activity {

	Button newGame_button, resume_button, options_button, guide_button;
	UnimonList allUnimonsList;
	private PlayerController playerController;
	private TrainerListController trainerListController;
	AlertDialog.Builder builder;
	private Context context;
	private DatabaseController dbController;
	public static MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		builder = new AlertDialog.Builder(this);

		dbController = new DatabaseController(this);

		// löscht alle Daten aus der Datenbank
		// dbController.clearDB();

		context = this.getApplicationContext();

		//initSound();
		// initDatabase();
		initUI();
	}
	
	private void checkMediaPlayerStatus(){
		if (mediaPlayer == null || !mediaPlayer.isPlaying()) { 
			mediaPlayer = null;
		}
	}

	private void initSound() {
		Log.d("hallo", "initSound");	
		
		checkMediaPlayerStatus();
		
		if (mediaPlayer == null) {
			SoundPlayer(this, R.raw.unimon_music);
		} 
		}
	

	@Override
	public void onBackPressed() {
		Log.d("hallo", "onBackPressed");
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
		System.exit(0);
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		finish();

	}

	@Override
	protected void onStart() {
		Log.d("hallo", "onStart");
		initSound();
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.d("hallo", "onStop");
		super.onStop();
	}
	

	@Override
	protected void onDestroy() {		
		Log.d("hallo", "onDestroy");
//		if (mediaPlayer.isPlaying()) {
//			mediaPlayer.stop();
//		}
		finish();
		super.onDestroy();
	}

	/*
	 * @Override protected void onDestroy() { playerDb.close();
	 * super.onDestroy(); }
	 * 
	 * private void initDatabase() { playerDb = new PlayerDatabase(this);
	 * playerDb.open(); }
	 */

	public static void SoundPlayer(Context context, int raw_id) {
		mediaPlayer = MediaPlayer.create(context, raw_id);
		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				mp.start();
			}
		});
		mediaPlayer.setLooping(true);
	}

	private void initUI() {
		Typeface font = Typeface.createFromAsset(getAssets(), "PokemonFont.ttf");

		newGame_button = (Button) findViewById(R.id.newGame_button);
		resume_button = (Button) findViewById(R.id.resume_button);
		options_button = (Button) findViewById(R.id.options_button);
		guide_button = (Button) findViewById(R.id.guide_button);
		
		newGame_button.setTypeface(font);
		resume_button.setTypeface(font);
		options_button.setTypeface(font);
		guide_button.setTypeface(font);
		setButtonsOnClick();
	}

	private void setButtonsOnClick() {

		resume_button.setEnabled(!dbController.isDbEmpty());

		newGame_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				playerController.reset();
				trainerListController.reset();
				final Intent newGame = new Intent(StartScreenActivity.this,
						NewGameActivity.class);
				newGame.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				if (!dbController.isDbEmpty()) {
					builder.setTitle(getResources().getString(
							R.string.newGame_alert_title));
					builder.setMessage(getResources().getString(
							R.string.newGame_alert_message));

					builder.setPositiveButton(
							getResources().getString(
									R.string.newGameButton_text),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startActivity(newGame);
									dialog.dismiss();
								}
							});
					builder.setNegativeButton(
							getResources().getString(R.string.cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				} else
					startActivity(newGame);
			}
		});
		resume_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// playerController.getInstanceFromDB(context);
				playerController.setInstance(dbController.getPlayer());
				trainerListController.setInstance(dbController.getTrainerList());
				Intent resume = new Intent(StartScreenActivity.this,
						MapActivity.class);
				startActivity(resume);
			}
		});
		options_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent options = new Intent(StartScreenActivity.this,
						OptionsScreenActivity.class);
				startActivity(options);
			}
		});
		guide_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent guide = new Intent(StartScreenActivity.this,
						GuideScreenActivity.class);
				startActivity(guide);
			}
		});
	}

}
