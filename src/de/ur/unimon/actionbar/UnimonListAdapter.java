package de.ur.unimon.actionbar;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;

public class UnimonListAdapter extends BaseAdapter {

	private Context context;
	LayoutInflater infalInflater;
	ArrayList<Unimon> unimons;
	private TextView unimonName, unimonLevel, health, unimonXp, xpText;
	private ImageView unimonImage;
	private ProgressBar healthBar, xpBar;

	public UnimonListAdapter(Context context, ArrayList<Unimon> unimons) {
		this.context = context;
		infalInflater = LayoutInflater.from(context);
		this.unimons = unimons;
	}

	@Override
	public int getCount() {
		return unimons.size();
	}

	@Override
	public Object getItem(int position) {
		return unimons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(R.layout.unimon_list_item, null);

		}
		Unimon unimon = (Unimon) getItem(position);

		if (unimon != null) {
			unimonImage = (ImageView) convertView
					.findViewById(R.id.unimon_image);
			unimonName = (TextView) convertView.findViewById(R.id.unimon_name);
			unimonLevel = (TextView) convertView
					.findViewById(R.id.unimon_level);
			healthBar = (ProgressBar) convertView.findViewById(R.id.healthBar);
			health = (TextView) convertView.findViewById(R.id.unimon_health);

			xpBar = (ProgressBar) convertView.findViewById(R.id.xpBar);
			unimonXp = (TextView) convertView.findViewById(R.id.unimon_xp);
			xpText = (TextView) convertView.findViewById(R.id.xp_text);

			unimonImage.setImageResource(unimon.getImage());
			unimonName.setText("Name: " + unimon.getName());
			unimonLevel.setText("Level: " + unimon.getLevel());
			healthBar.setMax(unimon.getMaxHealth());
			healthBar.setProgress(unimon.getHealth());
			xpBar.setMax(unimon.getXpPerLevel());
			xpBar.setProgress(unimon.getXp());

			int currentHealth = unimon.getHealth();
			int maxHealth = unimon.getMaxHealth();
			double healthPercentage = ((double) currentHealth) / maxHealth;

			if (healthPercentage >= 0.75) {
				healthBar.setProgressDrawable(convertView.getResources()
						.getDrawable(R.drawable.green_progress));
			} else if (healthPercentage <= 0.25) {
				healthBar.setProgressDrawable(convertView.getResources()
						.getDrawable(R.drawable.red_progress));
				;
			} else
				healthBar.setProgressDrawable(convertView.getResources()
						.getDrawable(R.drawable.orange_progress));

			health.setText(currentHealth + "/" + maxHealth);

			int currentXp = unimon.getXp();
			int maxXpPerLevel = unimon.getXpPerLevel();

			xpBar.setProgressDrawable(convertView.getResources().getDrawable(
					R.drawable.purple_progress));

			unimonXp.setText(currentXp + "/" + maxXpPerLevel);
		}
		return convertView;

	}

}
