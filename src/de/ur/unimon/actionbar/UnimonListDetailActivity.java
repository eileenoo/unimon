package de.ur.unimon.actionbar;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UnimonListDetailActivity extends Activity{
	
	TextView unimonInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_activity);
		initUI();

	}

	private void initUI() {
		unimonInfo = (TextView) findViewById(R.id.unimon_info);
		
	}

}
