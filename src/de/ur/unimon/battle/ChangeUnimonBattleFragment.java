package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.battle.AllOptionsBattleFragment.OnOptionsSelectorListener;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class ChangeUnimonBattleFragment extends Fragment {

	private OnUnimonChangedListener listener;
	private Button unimonTwoButton, unimonThreeButton;
	private Unimon[] currentBattleUnimonList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.change_unimon_battle_fragment,
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initCurrentBattleUnimonList();
		initButtons();
	}

	// Container Activity must implement this interface
	public interface OnUnimonChangedListener {
		public void onUnimonChanged(Unimon chosenUnimon, int index);

		public Unimon[] onGetBattleUnimonListArray();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnUnimonChangedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnUnimonChangedListener");
		}
	}

	private void initCurrentBattleUnimonList() {
		currentBattleUnimonList = listener.onGetBattleUnimonListArray();
	}

	private void initButtons() {
		unimonTwoButton = (Button) getView().findViewById(
				R.id.battle_unimon_two_button);
		unimonThreeButton = (Button) getView().findViewById(
				R.id.battle_unimon_three_button);

		String unimonTwoButtonText = currentBattleUnimonList[0].getName();
		String unimonThreeButtonText = currentBattleUnimonList[1].getName();
		String selectText = getString(R.string.battle_select_text);

		unimonTwoButton.setText(selectText + unimonTwoButtonText);
		int unimonTwoIndex = 0;
		clickToChangeUnimon(unimonTwoButton, unimonTwoIndex);

		if (currentBattleUnimonList[1] != null) {
			unimonThreeButton.setText(selectText + unimonThreeButtonText);
			int unimonThreeIndex = 1;
			clickToChangeUnimon(unimonThreeButton, unimonThreeIndex);
		} else {
			unimonThreeButton.setVisibility(View.GONE);
		}

	}

	private void clickToChangeUnimon(Button unimonNameButton,
			final int unimonNameIndex) {
		unimonNameButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Unimon chosenUnimon = currentBattleUnimonList[unimonNameIndex];
				Log.d("gewaltes unimon:", chosenUnimon.getName());
				listener.onUnimonChanged(chosenUnimon, unimonNameIndex);
				getFragmentManager().popBackStack();
			}
		});
	}
}
