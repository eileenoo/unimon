package de.ur.unimon.appstart;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class OptionsScreenActivity extends Activity {
	TextView sounds;
	RadioButton soundOn, soundOff;
	public static boolean soundOnButtonChecked;
	private DatabaseController dbController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_screen_activity);
		dbController = new DatabaseController(this);
		initUI();
	}

	private void initUI() {
		sounds = (TextView) findViewById(R.id.sound);
		soundOn = (RadioButton) findViewById(R.id.sound_on_radio);
		soundOff = (RadioButton) findViewById(R.id.sound_off_radio);
		if (dbController.isSoundTableEmpty()) {
			soundOn.setChecked(true);
			soundOff.setChecked(false);
		}
		else {
			soundOn.setChecked(dbController.getIsSoundOn());
			soundOff.setChecked(!dbController.getIsSoundOn());
		}
	}

	public void onRadioButtonClicked(View v) {
		// Is the button now checked?
		boolean checked = ((RadioButton) v).isChecked();
		// Check which radio button was clicked
		switch (v.getId()) {
		case R.id.sound_on_radio:
			if (checked)
				StartScreenActivity.SoundPlayer(this, R.raw.unimon_music);
			StartScreenActivity.mediaPlayer.start();
			StartScreenActivity.mediaPlayer.setLooping(true);
			soundOnButtonChecked = true;
			dbController.saveSound(true);
			break;
		case R.id.sound_off_radio:
			if (checked)
				if (StartScreenActivity.mediaPlayer.isPlaying()) {
					StartScreenActivity.mediaPlayer.stop();
					soundOnButtonChecked = false;
					dbController.saveSound(false);
				}
			break;
		}
	}
}
/*
 * radioGroupSound = (RadioGroup) findViewById(R.id.radio_group_sound);
 * radioGroupLanguage = (RadioGroup) findViewById (R.id.radio_group_language);
 * 
 * radioGroupSound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 * 
 * @Override public void onCheckedChanged(RadioGroup radioGroupSound, int
 * checkedId) { // find which radio button is selected if(checkedId ==
 * R.id.sound_on_radio) { StartScreenActivity.SoundPlayer(context,
 * R.raw.unimon_music); StartScreenActivity.mediaPlayer.start();
 * StartScreenActivity.mediaPlayer.setLooping(true); soundOnButtonChecked =
 * true; } else if (StartScreenActivity.mediaPlayer.isPlaying()) {
 * StartScreenActivity.mediaPlayer.stop(); soundOnButtonChecked = false; } }
 * 
 * });
 * 
 * 
 * radioGroupLanguage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 * 
 * @Override public void onCheckedChanged(RadioGroup radioGroupLanguage, int
 * checkedId) { // find which radio button is selected if(checkedId ==
 * R.id.language_english_radio) { setLocale("en"); } else setLocale("de"); }
 * 
 * 
 * });
 * 
 * }
 */

/*
 * public void onRadioButtonClicked(View v){
 * 
 * boolean checkedSound = ((RadioButton) v).isChecked(); boolean
 * checkedLanguageEnglish = ((RadioButton) languageEnglish).isChecked(); boolean
 * checkedLanguageGerman = ((RadioButton) languageGerman).isChecked();
 * 
 * 
 * switch (v.getId()) { case R.id.sound_on_radio: if (checkedSound)
 * StartScreenActivity.SoundPlayer(this, R.raw.unimon_music);
 * StartScreenActivity.mediaPlayer.start();
 * StartScreenActivity.mediaPlayer.setLooping(true); soundOnButtonChecked =
 * true; break; case R.id.sound_off_radio: if (checkedSound) if
 * (StartScreenActivity.mediaPlayer.isPlaying()) {
 * StartScreenActivity.mediaPlayer.stop(); soundOnButtonChecked = false; }
 * break; }
 * 
 * if (checkedLanguageEnglish){ setLocale("en"); } else
 * if(checkedLanguageGerman){ setLocale("de"); }
 * 
 * // Check which radio button was clicked switch (v.getId()) { case
 * R.id.language_english_radio: if (checkedLanguage) setLocale("en"); break;
 * case R.id.language_german_radio: if (checkedLanguage) setLocale("de"); break;
 * default: setLocale ("en"); }
 * 
 * }
 * 
 * 
 * public void setLocale(String lang) {
 * 
 * Locale myLocale = new Locale(lang); Resources res = getResources();
 * DisplayMetrics dm = res.getDisplayMetrics(); Configuration conf =
 * res.getConfiguration(); conf.locale = myLocale; res.updateConfiguration(conf,
 * dm); }
 */
