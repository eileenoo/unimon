package de.ur.unimon.buildings;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Spell;

public class SpellListAdapter extends BaseAdapter{
	
	private Context context;
	LayoutInflater inflater;
	ArrayList<Spell> spellList;
	private TextView spellName, spellLevel, currentSpellDamage, nextLevelSpellDamage;
	
	public SpellListAdapter(Context context, ArrayList<Spell> spellList){
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.spellList = spellList;
	}

	@Override
	public int getCount() {
		return spellList.size();
	}

	@Override
	public Object getItem(int position) {
		return spellList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.dompteur_skill_item, null);
		}
		
		Spell spell = (Spell) getItem(position);
		
		if (spell != null){
			spellName = (TextView) convertView.findViewById(R.id.spell_name);
			spellLevel = (TextView) convertView.findViewById(R.id.level);
			currentSpellDamage = (TextView) convertView.findViewById(R.id.current_spell_damage);
			nextLevelSpellDamage = (TextView) convertView.findViewById(R.id.next_level_spell_damage);
			spellName.setText(spell.getSpellName());
			spellLevel.setText("Level: "+spell.getSpellLevel());
			currentSpellDamage.setText("Damage: "+spell.getDamageforDisplay(spell.getSpellLevel()));
			nextLevelSpellDamage.setText("Damage at level "+spell.getNextLevel()+": "+spell.getDamageforDisplay(spell.getNextLevel()));
		}
		
		return convertView;
	}

}
