package de.ur.unimon.appstart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.database.AppDatabase;
import de.ur.unimon.database.PlayerDatabase;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.UnimonList;

public class StartScreenActivity extends Activity {

	Button newGame_button;
	Button resume_button;
	Button options_button;
	Button guide_button;
	Context context;
	UnimonList allUnimonsList;
	PlayerController playerController;
	PlayerDatabase playerDb;
	AlertDialog.Builder builder;
	public static MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		builder = new AlertDialog.Builder(this);
		context = this.getApplicationContext();
		SoundPlayer(this,R.raw.unimon_music);
		
		//initDatabase();
		initUI();		
	}
	
	/*@Override
	protected void onResume(){
		mediaPlayer.stop();
		SoundPlayer(this,R.raw.unimon_music);
	}*/
	
	/*@Override
	protected void onDestroy() {
		playerDb.close();
		super.onDestroy();
	}
	
	private void initDatabase() {
		playerDb = new PlayerDatabase(this);
		playerDb.open();
	}*/
	
    public static void SoundPlayer(Context context,int raw_id){
    	mediaPlayer = MediaPlayer.create(context, raw_id);
    	mediaPlayer.setLooping(true); // Set looping    	
        //mediaPlayer.release();
    	mediaPlayer.start();
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
    			playerController.getInstance();
    			builder.setTitle(getResources().getString(R.string.newGame_alert_title));
				builder.setMessage(getResources().getString(R.string.newGame_alert_message));

				builder.setPositiveButton(getResources().getString(R.string.newGameButton_text),
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent newGame = new Intent (StartScreenActivity.this, NewGameActivity.class);
				    			newGame.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				    			startActivity(newGame);
								dialog.dismiss();
							}
						});
				builder.setNegativeButton(getResources().getString(R.string.cancel),
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {									
								dialog.dismiss();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
    	});
    	resume_button.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {	
    			playerController.getInstanceFromDB();
    			Intent resume = new Intent (StartScreenActivity.this, MapActivity.class);
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
