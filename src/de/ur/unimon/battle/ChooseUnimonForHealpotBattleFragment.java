package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ChooseUnimonForHealpotBattleFragment extends Fragment {

	private OnGetBattleUnimonsListListener listener;
	private Button battleUnimonOneButton, battleUnimonTwoButton, battleUnimonThreeButton;
	private Unimon[] unimonBattlelist;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			listener = (OnGetBattleUnimonsListListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnGetBattleUnimonsListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.select_unimon_for_healpot_battle_fragment,
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initButtons();
		initUnimonList();
		initUI();
		initClickListeners();
	}

	public interface OnGetBattleUnimonsListListener {
		public Unimon[] onGetBattleUnimonsList();
		public void onHealpotUsedOnUnimon(Unimon unimon);
	}

	private void initButtons() {
		battleUnimonOneButton = (Button) getView().findViewById(R.id.battle_select_unimon_one_for_healpot_button);
		battleUnimonTwoButton = (Button) getView().findViewById(R.id.battle_select_unimon_two_for_healpot_button);
		battleUnimonThreeButton = (Button) getView().findViewById(R.id.battle_select_unimon_three_for_healpot_button);

	}

	private void initUnimonList() {
		unimonBattlelist = listener.onGetBattleUnimonsList();
//		Log.d("battleUnimon", unimonBattlelist[0].getName());
	}
	
	private void initUI() {
		battleUnimonOneButton.setText(unimonBattlelist[0].getName());
		if (unimonBattlelist[1] != null) {
			battleUnimonTwoButton.setText(unimonBattlelist[1].getName());
		} else {
			battleUnimonTwoButton.setVisibility(View.GONE);
		}
		if (unimonBattlelist[2] != null) {
			battleUnimonThreeButton.setText(unimonBattlelist[2].getName());
		} else {
			battleUnimonThreeButton.setVisibility(View.GONE);
		}
	}
	
	private void initClickListeners() {
		battleUnimonOneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				listener.onHealpotUsedOnUnimon(unimonBattlelist[0]);
				getFragmentManager().popBackStack();
			}
		});
		
		battleUnimonTwoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				listener.onHealpotUsedOnUnimon(unimonBattlelist[1]);
				getFragmentManager().popBackStack();
			}
		});
		
		battleUnimonThreeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				listener.onHealpotUsedOnUnimon(unimonBattlelist[2]);
//				getFragmentManager().popBackStack();
				getFragmentManager().popBackStack("allOptionsFragment", 0);
			}
		});
	}

}
