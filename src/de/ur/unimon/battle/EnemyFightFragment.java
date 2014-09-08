package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Spell;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class EnemyFightFragment extends Fragment {

	private OnGetEnemyInformationListener listener;
	private TextView enemyName, textPartOne, textPartTwo;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			listener = (OnGetEnemyInformationListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnGetEnemyInformationListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.enemy_fight_fragment,
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setUI();
	}

	private void setUI() {
		textPartOne = (TextView) getView().findViewById(R.id.enemy_fight_textview_one);
		textPartTwo = (TextView) getView().findViewById(R.id.enemy_fight_textview_two);
		enemyName = (TextView) getView().findViewById(R.id.enemy_fight_enemyname_textview);
		
		String enemyNameString = listener.onGetEnemyName();
		enemyName.setText(enemyNameString);
		
	}

	public interface OnGetEnemyInformationListener {
		public String onGetEnemyName();
	}

}
