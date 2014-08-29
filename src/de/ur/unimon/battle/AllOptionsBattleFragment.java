// Quelle: http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
// Quelle: http://www.mysamplecode.com/2012/08/android-fragment-example.html
// Quelle: http://developer.android.com/training/basics/fragments/communicating.html
// Quelle: http://examples.javacodegeeks.com/android/core/app/fragment/android-fragments-example/
// Quelle: http://stackoverflow.com/questions/21028786/how-do-i-open-a-new-fragment-from-another-fragment
// Quelle: http://stackoverflow.com/questions/10863572/programmatically-go-back-to-the-previous-fragment-in-the-backstack

package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AllOptionsBattleFragment extends Fragment {

	// Ein Fehler kšnnte sein: Bundle extras bla in eine andere Methode
	// reinschreiben... zB in onCreateView()

	// In diesem Fragment werden alle Mšglichkeiten des Spielers im Kampf
	// angezeigt (AttackButton, ChangeUnimonButton, EscapeButton, ItemButton).
	// Sobald einer dieser Buttons gecklickt wird, wird dieses Fragment mit
	// einem neuen Fragment (AttackFragment, ChangeUnimonFragment, ItemFragment
	// ausgetauscht.

	private OnOptionsSelectorListener listener;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private Button attackButton, useItemButton, escapeButton,
			changeUnimonButton;
	private boolean currentUnimonListHasContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getArguments();
		currentUnimonListHasContent = extras
				.getBoolean("CurrentUnimonListContent");

		fragmentManager = this.getFragmentManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.all_options_battle_fragment,
				container, false);
		return view;
	}
	
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		enableBackButton();
		initButtons();
		initClickListeners();
	}

	private void enableBackButton() {
		getView().setFocusableInTouchMode(true);
		getView().setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					return true;
				}
				return false;
			}
		});
	}

	// Container Activity must implement this interface
	public interface OnOptionsSelectorListener {
		public void onEscapeSuccessfull();

		public void onEscapeFailed();

		public boolean onIsEscapeAvailable();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnOptionsSelectorListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnOptionsSelectorListener");
		}
	}

	private void initButtons() {
		attackButton = (Button) getView().findViewById(
				R.id.battle_attack_button);
		useItemButton = (Button) getView().findViewById(
				R.id.battle_use_item_button);
		escapeButton = (Button) getView().findViewById(
				R.id.battle_escape_button);
		changeUnimonButton = (Button) getView().findViewById(
				R.id.battle_change_unimon_button);
	}

	private void initClickListeners() {
		attackButtonClicked();
		itemButtonClicked();
		escapeButtonClicked();
		changeUnimonButtonClicked();
	}

	private void changeUnimonButtonClicked() {
		if (!currentUnimonListHasContent) {
			changeUnimonButton.setClickable(false);
		} else {
			changeUnimonButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ChangeUnimonBattleFragment changeUnimonFrag = new ChangeUnimonBattleFragment();
					fragmentTransaction = fragmentManager.beginTransaction();
					fragmentTransaction.replace(R.id.battle_activity_layout,
							changeUnimonFrag, "ChangeUnimonFragment");
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
				}
			});
		}
	}

	private void escapeButtonClicked() {
		escapeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener.onIsEscapeAvailable()) {
					listener.onEscapeSuccessfull();
				} else {
					listener.onEscapeFailed();
				}
			}
		});
	}

	private void itemButtonClicked() {
		useItemButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ChooseItemFragment itemFrag = new ChooseItemFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.battle_activity_layout,
						itemFrag, "ItemBattleFragment");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});
	}

	private void attackButtonClicked() {
		attackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AttackBattleFragment attackFrag = new AttackBattleFragment();
				fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.battle_activity_layout,
						attackFrag, "AttackBattleFragment");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});
	}
}
