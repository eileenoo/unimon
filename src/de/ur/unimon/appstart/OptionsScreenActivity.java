package de.ur.unimon.appstart;


import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class OptionsScreenActivity extends Activity{
	
	TextView sounds;
	TextView language;
	RadioButton soundOn;
	RadioButton soundOff;
	RadioButton languageEnglish;
	RadioButton languageGerman;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_screen_activity);
		initUI();
	}
	
	private void initUI(){
		sounds = (TextView) findViewById (R.id.sound);	
		soundOn = (RadioButton) findViewById (R.id.sound_on_radio);
		soundOff = (RadioButton) findViewById (R.id.sound_off_radio);
		language = (TextView) findViewById (R.id.language);
		languageEnglish = (RadioButton) findViewById (R.id.language_english_radio);
		languageGerman = (RadioButton) findViewById (R.id.language_german_radio);
		
	}
	
	public void onRadioButtonClicked(View v) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton)v).isChecked();
	    
	    // Check which radio button was clicked
	    switch(v.getId()) {
	        case R.id.sound_on_radio:
	            if (checked)
	                
	            break;
	        case R.id.sound_off_radio:
	            if (checked)
	                
	            break;
	    }
	}
}

