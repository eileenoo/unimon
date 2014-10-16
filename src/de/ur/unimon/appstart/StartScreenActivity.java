package de.ur.unimon.appstart;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.trainer.TrainerListController;
import de.ur.unimon.unimons.UnimonList;

public class StartScreenActivity extends Activity {

	Button newGame_button, resume_button, options_button, guide_button;
	ImageButton sound_button;
	public static boolean soundOnChecked;
	UnimonList allUnimonsList;
	private PlayerController playerController;
	private TrainerListController trainerListController;
	AlertDialog.Builder builder;
	Context context;
	private DatabaseController dbController;
	public static MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		builder = new AlertDialog.Builder(this);

		dbController = new DatabaseController(this);

		context = this.getApplicationContext();
		initUI();
	}

	private void checkMediaPlayerStatus() {
		if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
			mediaPlayer = null;
		}
	}

	private void initSound() {
		checkMediaPlayerStatus();

		if (mediaPlayer == null) {
			if (!dbController.isSoundTableEmpty()) {
				if (dbController.getIsSoundOn()) {
					SoundPlayer(this, R.raw.unimon_music);
				}
			} else
				SoundPlayer(this, R.raw.unimon_music);
		}
	}

	@Override
	public void onBackPressed() {
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
		initSound();
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}

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
		Typeface font = Typeface
				.createFromAsset(getAssets(), "PokemonFont.ttf");

		newGame_button = (Button) findViewById(R.id.newGame_button);
		resume_button = (Button) findViewById(R.id.resume_button);
		guide_button = (Button) findViewById(R.id.guide_button);

		newGame_button.setTypeface(font);
		resume_button.setTypeface(font);
		guide_button.setTypeface(font);

		sound_button = (ImageButton) findViewById(R.id.image_button_sound);
		if (dbController.isSoundTableEmpty()) {
			sound_button.setImageResource(R.drawable.sound_on_icon);
		} else {
			if (dbController.getIsSoundOn() == true) {
				sound_button.setImageResource(R.drawable.sound_on_icon);
			} else if (!dbController.getIsSoundOn()) {
				sound_button.setImageResource(R.drawable.sound_off_icon);
			}
		}
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
									dbController.clearDB();
									startActivity(newGame);
									newGame.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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
				newGame.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
		resume_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				playerController.setInstance(dbController.getPlayer());
				trainerListController.setInstance(dbController.getTrainerList());
				Intent resume = new Intent(StartScreenActivity.this,
						MapActivity.class);
				resume.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(resume);
			}
		});
		guide_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent guide = new Intent(StartScreenActivity.this,
						GuideScreenActivity.class);
				startActivity(guide);
				guide.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
		sound_button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Drawable drawable = sound_button.getDrawable();
				if (drawable.getConstantState().equals(
						getResources().getDrawable(R.drawable.sound_on_icon)
								.getConstantState())
						&& mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
					soundOnChecked = false;
					dbController.saveSound(false);
					sound_button.setImageResource(R.drawable.sound_off_icon);
				} else if (drawable.getConstantState().equals(
						getResources().getDrawable(R.drawable.sound_off_icon)
								.getConstantState())) {
					SoundPlayer(context, R.raw.unimon_music);
					mediaPlayer.start();
					mediaPlayer.setLooping(true);
					soundOnChecked = true;
					dbController.saveSound(true);
					sound_button.setImageResource(R.drawable.sound_on_icon);
				}
			}

		});

	}
}
