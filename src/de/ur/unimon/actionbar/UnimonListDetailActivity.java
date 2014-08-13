package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UnimonListDetailActivity extends Activity{
	
	TextView unimonInfo;
	private String chosenUnimonName;
	private Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_activity);
		extras = this.getIntent().getExtras();
		initUI();

	}

	private void initUI() {
		unimonInfo = (TextView) findViewById(R.id.unimon_info);
		chosenUnimonName = extras.getString(chosenUnimonName);
		unimonInfo.setText(chosenUnimonName);
	}

}
