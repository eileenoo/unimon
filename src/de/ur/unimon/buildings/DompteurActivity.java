package de.ur.unimon.buildings;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class DompteurActivity extends FragmentActivity{

	private static Player player;
	private PlayerController playerController;
	Button newSkill, improveSkill;
	TextView dompteurText;
	private static ArrayList <Unimon> ownedUnimonList;
	UnimonFragmentPagerAdapter unimonFragmentPagerAdapter;
	ViewPager viewPager;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dompteur_activity);
		
		initUI();
		initFragmentPagerAdapter();
		initClickListener();
	}
	
	@Override
	protected void onResume() {
		unimonFragmentPagerAdapter.notifyDataSetChanged();
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		DatabaseController databaseController = new DatabaseController(this);
		databaseController.save();
		super.onDestroy();
	}

	private void initUI() {
		Typeface font = Typeface.createFromAsset(getAssets(), "PokemonFont.ttf");
		
		player = playerController.getInstance();
		ownedUnimonList = player.getUnimonList();
		dompteurText = (TextView) findViewById(R.id.dompteur_text);
		newSkill = (Button) findViewById(R.id.dompteur_new_skill);
		newSkill.setTypeface(font);
		improveSkill = (Button) findViewById(R.id.dompteur_improve_skill);
		improveSkill.setTypeface(font);
	}
	
	private void initClickListener() {
		newSkill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = viewPager.getCurrentItem();			
				if (ownedUnimonList.get(index).getSkillPoints() == 0) {
					Toast toast = Toast.makeText(getApplicationContext(), "You dont have any Skillpoints!",
							Toast.LENGTH_SHORT);
					toast.show();
				}
				else {
					Intent toLearnNewSkill = new Intent(DompteurActivity.this, DompteurSkillActivity.class);
					toLearnNewSkill.putExtra("unimonPosition", index);
					toLearnNewSkill.putExtra("skillType", "newSkill");
					startActivity(toLearnNewSkill);
				}
			}
		});
				
		improveSkill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = viewPager.getCurrentItem();
				if (ownedUnimonList.get(index).getSkillPoints() == 0) {
					Toast toast = Toast.makeText(getApplicationContext(), "You dont have any Skillpoints!",
							Toast.LENGTH_SHORT);
					toast.show();
				}
				else {
					Intent toImproveSkill = new Intent(DompteurActivity.this, DompteurSkillActivity.class);
					toImproveSkill.putExtra("unimonPosition", index);
					toImproveSkill.putExtra("skillType", "improveSkill");
					startActivity(toImproveSkill);
				}
			}
		});
		
	}

	private void initFragmentPagerAdapter() {
		unimonFragmentPagerAdapter = new UnimonFragmentPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.dompteur_pager);
		viewPager.setAdapter(unimonFragmentPagerAdapter);
	}


 public static class UnimonFragmentPagerAdapter extends FragmentPagerAdapter{

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public UnimonFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
    public Fragment getItem(int position) {
        SwipeFragment fragment = new SwipeFragment();
        return SwipeFragment.newInstance(position);
    }

	@Override
	public int getCount() {
		return player.getUnimonList().size();
	}
 }



public static class SwipeFragment extends Fragment{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View swipeView = inflater.inflate(R.layout.dompteur_swipe_detail, container, false);
		TextView unimonName = (TextView) swipeView.findViewById(R.id.dompteur_unimon_name);
		TextView healthText = (TextView) swipeView.findViewById(R.id.dompteur_health_text);
		TextView unimonHealth = (TextView) swipeView.findViewById(R.id.dompteur_unimon_health);
		TextView unimonLevel = (TextView) swipeView.findViewById(R.id.dompteur_unimon_level);
		TextView unimonXp = (TextView) swipeView.findViewById(R.id.dompteur_unimon_xp);
		TextView xpText = (TextView) swipeView.findViewById(R.id.dompteur_xp_text);
		TextView unimonSpell = (TextView) swipeView.findViewById(R.id.dompteur_spell);
		TextView skillPoints = (TextView) swipeView.findViewById(R.id.dompteur_skillpoints);
		ProgressBar healthBar = (ProgressBar) swipeView.findViewById(R.id.dompteur_healthBar);
		ProgressBar xpBar = (ProgressBar) swipeView.findViewById(R.id.dompteur_xpBar);
		ImageView unimonImage = (ImageView) swipeView.findViewById(R.id.dompteur_unimon_image);
		
		Bundle args = getArguments();
		int position = args.getInt("position");;
		
		unimonName.setText(ownedUnimonList.get(position).getName());
		unimonLevel.setText("Level: "+ownedUnimonList.get(position).getLevel());
		unimonImage.setImageResource(ownedUnimonList.get(position).getImage());
		
		String spellText = "";
		for (int i = 0; i<ownedUnimonList.get(position).getOwnedSpells().size(); i++){
			spellText += ownedUnimonList.get(position).getSpellBySpellNumber(i).getSpellName() + "\n";
		}
		unimonSpell.setText(spellText);
				
		int currentHealth = ownedUnimonList.get(position).getHealth();
		int maxHealth = ownedUnimonList.get(position).getMaxHealth();
		double healthPercentage =((double) currentHealth) / maxHealth;
		
		healthBar.setMax(maxHealth);
		healthBar.setProgress(currentHealth);
					
		if (healthPercentage >= 0.75) {
			healthBar.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));
		} else if (healthPercentage <= 0.25){
			healthBar.setProgressDrawable(getResources().getDrawable(R.drawable.red_progress));;
		} else healthBar.setProgressDrawable(getResources().getDrawable(R.drawable.orange_progress));
		
		unimonHealth.setText(currentHealth+"/"+maxHealth);
		
		int currentXp = ownedUnimonList.get(position).getXp();
		int maxXpPerLevel = ownedUnimonList.get(position).getXpPerLevel();
		
		xpBar.setMax(maxXpPerLevel);
		xpBar.setProgress(currentXp);
	
		xpBar.setProgressDrawable(getResources().getDrawable(R.drawable.purple_progress));
		unimonXp.setText(currentXp+"/"+maxXpPerLevel);
		
		skillPoints.setText("Skillpoints: "+ownedUnimonList.get(position).getSkillPoints());
		return swipeView;
	}
	
	static SwipeFragment newInstance(int position) {
        SwipeFragment swipeFragment = new SwipeFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        swipeFragment.setArguments(args);
        return swipeFragment;
    }


}

	
}