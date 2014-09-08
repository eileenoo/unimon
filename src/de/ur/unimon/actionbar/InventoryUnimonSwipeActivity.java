package de.ur.unimon.actionbar;

import java.util.ArrayList;

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
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerController;
import de.ur.unimon.unimons.Unimon;

public class InventoryUnimonSwipeActivity extends FragmentActivity{
	
	UnimonFragmentPagerAdapter unimonFragmentPagerAdapter;
	ViewPager viewPager;
	private  PlayerController playerController;
	public static ArrayList<Unimon> ownedUnimonList;
	Button useHealPot, revive;
	public static Player player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_unimon_swipe_activity);
		
		player = playerController.getInstance();
		ownedUnimonList = player.getUnimonList();
				
		initFragmentPagerAdapter();
		initClickListener();

	}

	private void initClickListener() {
		useHealPot = (Button) findViewById(R.id.inventory_use_healPot);
		useHealPot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = viewPager.getCurrentItem();
				
				if (player.getUnimonList().get(index).getHealth() != player.getUnimonList().get(index).getMaxHealth()) {
					if (player.getInventory().healpotAvailable()){
						player.takeHealpotOutOfInventory();				
						ownedUnimonList.get(index).useHealpot();
						unimonFragmentPagerAdapter.notifyDataSetChanged();
					}
					else {
						Toast toast = Toast.makeText(getApplicationContext(), "You don´t have any Healpots left!",
								Toast.LENGTH_SHORT);
						toast.show();
					}
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "This Unimon is already at maximum Health!",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		revive = (Button) findViewById(R.id.inventory_use_revive);
		revive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = viewPager.getCurrentItem();
				if (!player.getUnimonList().get(index).isAlive()){
					if (player.getInventory().reviveAvailable()){
						player.takeReviveOutOfInventory();				
						ownedUnimonList.get(index).setAlive(true);
						ownedUnimonList.get(index).setToMaxHealth();
						unimonFragmentPagerAdapter.notifyDataSetChanged();
					} else {
						Toast toast = Toast.makeText(getApplicationContext(), "You don´t have any Revives left!",
								Toast.LENGTH_SHORT);
						toast.show();
					}
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "This Unimon is still alive",
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
	}

	private void initFragmentPagerAdapter() {
		unimonFragmentPagerAdapter = new UnimonFragmentPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.inventory_pager);
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
		View swipeView = inflater.inflate(R.layout.inventory_unimon_swipe_detail, container, false);
		TextView unimonName = (TextView) swipeView.findViewById(R.id.inventory_unimon_name);
		TextView unimonHealth = (TextView) swipeView.findViewById(R.id.inventory_unimon_health);
		TextView unimonLevel = (TextView) swipeView.findViewById(R.id.inventory_level);
		TextView unimonSpell = (TextView) swipeView.findViewById(R.id.inventory_spell);
		ProgressBar healthBar = (ProgressBar) swipeView.findViewById(R.id.inventory_healthBar);
		ProgressBar xpBar = (ProgressBar) swipeView.findViewById(R.id.inventory_xpBar);
		ImageView unimonImage = (ImageView) swipeView.findViewById(R.id.inventory_unimon_image);
		
		Bundle args = getArguments();
		int position = args.getInt("position");;
		
		unimonName.setText(ownedUnimonList.get(position).getName());
		unimonLevel.setText("Level: "+ownedUnimonList.get(position).getLevel());
		
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
		
		ownedUnimonList.get(position).setXp(10);
		int currentXp = ownedUnimonList.get(position).getXp();
		int maxXpPerLevel = ownedUnimonList.get(position).getXpPerLevel();
		
		xpBar.setMax(maxXpPerLevel);
		xpBar.setProgress(currentXp);
	
		xpBar.setProgressDrawable(getResources().getDrawable(R.drawable.purple_progress));
		
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