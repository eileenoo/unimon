package de.ur.unimon.mapoverview;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.battle.ChooseBattleUnimonsActivity;
import de.ur.unimon.buildings.DompteurActivity;
import de.ur.unimon.buildings.HospitalActivity;
import de.ur.unimon.buildings.ShopActivity;
import de.ur.unimon.trainer.Trainer;
import de.ur.unimon.trainer.TrainerListController;

public class EnterAlertFragment extends Fragment {

	Button okButton, cancelButton;
	TextView questionView;
	String building, questionText;
	int trainerID;
	private ArrayList<Trainer> trainerList;
	private TrainerListController trainerListController;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.in_range_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		trainerList = trainerListController.getInstance().getTrainerList();
		Bundle extras = getArguments();
		building = extras.getString("building");
		if (building == "Shop" || building == "Hospital"
				|| building == "Dompteur") {
			questionText = "Do you want to enter the " + building + "?";
		} else {
			trainerID = extras.getInt("trainerID");
			questionText = "Do you want to fight "
					+ trainerList.get(trainerID).getName() + "?";
		}
		questionView = (TextView) getView().findViewById(
				R.id.want_to_enter_text);
		questionView.setText(questionText);

		initClickListener();
	}

	private void initClickListener() {
		okButton = (Button) getView().findViewById(R.id.enter);
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (building) {
				case "Shop":
					Intent toShop = new Intent(getActivity(),
							ShopActivity.class);
					startActivity(toShop);
					break;
				case "Hospital":
					Intent toHospital = new Intent(getActivity(),
							HospitalActivity.class);
					startActivity(toHospital);
					break;
				case "Dompteur":
					Intent toDompteur = new Intent(getActivity(),
							DompteurActivity.class);
					startActivity(toDompteur);
					break;
				case "Trainer":
					Intent toBattle = new Intent(getActivity(),
							ChooseBattleUnimonsActivity.class);
					Bundle extras = new Bundle();
					extras.putInt("trainerID", trainerID);
					toBattle.putExtras(extras);
					startActivity(toBattle);
				}
			}
		});

		cancelButton = (Button) getView().findViewById(R.id.do_not_enter);
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeFragment();
			}
		});

	}

	private void closeFragment() {
		FragmentTransaction transaction = getActivity().getFragmentManager()
				.beginTransaction();
		transaction.setCustomAnimations(R.animator.slide_in_bottom,
				R.animator.slide_out_top);
		transaction.remove(this).commit();
	}

}
