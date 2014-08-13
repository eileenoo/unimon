package de.ur.unimon.buildings;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class DompteurActivity extends Activity{
	
	Button newSkill, improveSkill;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_activity);
		initUI();
}

	private void initUI() {
		newSkill = (Button) findViewById(R.id.inventory);
		improveSkill = (Button) findViewById(R.id.unimons);
		
	}
}