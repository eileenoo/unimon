package de.ur.unimon.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;

public class UnimonListDetailActivity extends Activity{
	
	private TextView unimonInfo;
	private String chosenUnimonName;
	private Bundle extras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unimon_list_item_detail);
		extras = this.getIntent().getExtras();
		initUI();

	}

	private void initUI() {
		unimonInfo = (TextView) findViewById(R.id.unimon_info);
		chosenUnimonName = extras.getString("chosen_unimon_name");
		unimonInfo.setText(chosenUnimonName);
	}

}
