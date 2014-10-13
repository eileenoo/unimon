package de.ur.unimon.start.newgame;

import java.util.ArrayList;

import android.content.Intent;
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
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.unimons.Unimon;
import de.ur.unimon.unimons.UnimonList;

public class ChooseUnimonActivity extends FragmentActivity{
	
	UnimonFragmentPagerAdapter unimonFragmentPagerAdapter;
	ViewPager viewPager;
	private  PlayerController playerController;
	public static ArrayList<Unimon> startUnimonList = new UnimonList().getStartUnimonList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_unimon_activity);
		initFragmentPagerAdapter();
		initClickListener();
	}
	
	@Override
	public void onBackPressed() {
	}


	private void initClickListener() {
		Button chooseButton = (Button) findViewById(R.id.choose_that_unimon_button);
		chooseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int index = viewPager.getCurrentItem();
//				MyAdapter adapter = ((MyAdapter)mViewPager.getAdapter());
//				MyFragment fragment = adapter.getFragment(index);
				
				playerController.getInstance().addUnimon(startUnimonList.get(index));
				Intent toMap = new Intent(ChooseUnimonActivity.this, MapActivity.class);
				startActivity(toMap);
				
			}
		});
		
	}

	private void initFragmentPagerAdapter() {
		unimonFragmentPagerAdapter = new UnimonFragmentPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(unimonFragmentPagerAdapter);
	}


 public static class UnimonFragmentPagerAdapter extends FragmentPagerAdapter{

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
		return 3;
	}
 }



public static class SwipeFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View swipeView = inflater.inflate(R.layout.choose_unimon_detail, container, false);
		TextView swipeUnimonsInfo = (TextView) swipeView.findViewById(R.id.swipe_unimons_info);
		TextView unimonName = (TextView) swipeView.findViewById(R.id.choose_unimon_name);
		TextView unimonHealth = (TextView) swipeView.findViewById(R.id.choose_unimon_health);
		TextView unimonLevel = (TextView) swipeView.findViewById(R.id.choose_unimon_level);
		TextView unimonSpell = (TextView) swipeView.findViewById(R.id.choose_unimon_spell_headline);
		ProgressBar healthBar = (ProgressBar) swipeView.findViewById(R.id.choose_healthBar);
		ImageView unimonImage = (ImageView) swipeView.findViewById(R.id.choose_unimon_image);
		
		Bundle args = getArguments();
		int position = args.getInt("position");;
		
		unimonImage.setImageResource(startUnimonList.get(position).getImage());
		swipeUnimonsInfo.setText(getResources().getString(R.string.swipe_unimons_info_text));
		unimonName.setText(startUnimonList.get(position).getName());
		unimonHealth.setText(startUnimonList.get(position).getMaxHealth()+"/"+startUnimonList.get(position).getMaxHealth());
		unimonLevel.setText(getResources().getString(R.string.level_text)+startUnimonList.get(position).getLevel());
		unimonSpell.setText(getResources().getString(R.string.spells_headline)+startUnimonList.get(position).getSpellBySpellNumber(0).getSpellName()+" (damage: "+startUnimonList.get(position).getSpellBySpellNumber(0).getBaseDamage()+")");
		healthBar.setMax(startUnimonList.get(position).getMaxHealth());
		healthBar.setProgress(startUnimonList.get(position).getMaxHealth());
		healthBar.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));
		
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
