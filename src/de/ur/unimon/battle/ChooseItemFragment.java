package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.battle.ChangeUnimonBattleFragment.OnUnimonChangedListener;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChooseItemFragment extends Fragment {

	private OnChooseItemListener listener;
	private Button healpotButton, uniballButton;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnChooseItemListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnChooseItemListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.items_battle_fragment, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initButtons();
		itemButtonClicked();
	}

	public interface OnChooseItemListener {
		public boolean onHealpotAvailable();

		public boolean onUniballAvailable();

		public void onUniballButtonClicked();

	}

	private void initButtons() {
		healpotButton = (Button) getView().findViewById(
				R.id.battle_healpot_button);
		uniballButton = (Button) getView().findViewById(
				R.id.battle_uniball_button);

		if (listener.onHealpotAvailable()) {
			healpotButton.setClickable(true);
		} else {
			healpotButton.setClickable(false);
		}

		if (listener.onUniballAvailable()) {
			uniballButton.setClickable(true);
		} else {
			uniballButton.setClickable(false);
		}
	}

	private void itemButtonClicked() {
		healpotButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ChooseUnimonForHealpotBattleFragment chooseUnimonForItemFragment = new ChooseUnimonForHealpotBattleFragment();
				FragmentTransaction fragmentTransaction = getFragmentManager()
						.beginTransaction();
				fragmentTransaction.replace(R.id.battle_activity_layout,
						chooseUnimonForItemFragment,
						"ChooseUnimonForItemBattleFragment");
				getFragmentManager().popBackStack();
				fragmentTransaction.commit();
			}
		});

		uniballButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onUniballButtonClicked();
				getFragmentManager().popBackStack();
			}
		});
	}

}
