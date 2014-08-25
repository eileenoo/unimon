// Quelle: http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
// Quelle: http://www.mysamplecode.com/2012/08/android-fragment-example.html
// Quelle: http://developer.android.com/training/basics/fragments/communicating.html
// Quelle: http://examples.javacodegeeks.com/android/core/app/fragment/android-fragments-example/

package de.ur.unimon.battle;

import de.ur.mi.android.excercises.starter.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AllOptionsBattleFragment extends Fragment {

	// Ein Fehler kšnnte sein: Bundle extras bla in eine andere Methode
	// reinschreiben... zB in onCreateView()

	// In diesem Fragment werden alle Mšglichkeiten des Spielers im Kampf
	// angezeigt (AttackButton, ChangeUnimonButton, EscapeButton, ItemButton).
	// Sobald einer dieser Buttons gecklickt wird, wird die ParentActivity durch
	// das Interface OnOptionsSelectorListener benachrichtigt und dort wird dann
	// AllOptionsBattleFragment mit einem anderen Fragment ausgetauscht

	private OnOptionsSelectorListener listener;
	private Button attackButton, useItemButton, escapeButton,
			changeUnimonButton;
	private boolean currentUnimonListHasContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getArguments();
		currentUnimonListHasContent = extras
				.getBoolean("CurrentUnimonListContent");
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

		initButtons();
		initClickListeners();
	}

	// Container Activity must implement this interface
	public interface OnOptionsSelectorListener {
		public void onEscapeButtonClicked();

		public void onAttackButtonClicked();

		public void onItemButtonClicked();

		public void onChangeUnimonButtonClicked();
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
					listener.onChangeUnimonButtonClicked();
				}
			});
		}
	}

	private void escapeButtonClicked() {
		escapeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onEscapeButtonClicked();
			}
		});
	}

	private void itemButtonClicked() {
		useItemButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onItemButtonClicked();
			}
		});
	}

	private void attackButtonClicked() {
		attackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onAttackButtonClicked();
			}
		});
	}
}
