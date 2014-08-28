package de.ur.unimon.battle;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.battle.AllOptionsBattleFragment.OnOptionsSelectorListener;
import de.ur.unimon.unimons.Spell;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AttackBattleFragment extends Fragment {

	private ListView spellListView;
	private ListAdapter spellListViewAdapter;
	private ArrayList<Spell> spellList;
	private OnSpellSelectedListener listener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			listener = (OnSpellSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnOptionsSelectorListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.attack_battle_fragment,
				container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initListAdapter();
		initListView();
	}

	public interface OnSpellSelectedListener {
		public void onSpellSelected(Spell chosenSpell);
		public ArrayList<Spell> getSpellList();
	}

	private void initListAdapter() {
		spellList = listener.getSpellList();
		spellListView = (ListView) getView().findViewById(R.id.spell_list_view);
		Context context = getActivity();
		spellListViewAdapter = new AttackFragmentListAdapter(context, spellList);
		spellListView.setAdapter(spellListViewAdapter);
	}
	
	private void initListView() {
		spellListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Spell chosenSpell = (Spell) spellListView.getItemAtPosition(position);
				listener.onSpellSelected(chosenSpell);
				getFragmentManager().popBackStack();
			}
		});
	}
}
