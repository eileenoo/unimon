package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.unimons.Unimon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryActivity extends Activity {

	private TextView money, healpotName, uniballName, reviveName,
			protectorName, healpotCount, uniballCount, reviveCount,
			protectorCount;

	private Button useItemButton, useProtectorButton;
	private ImageView healpotImage, uniballImage, reviveImage, protectorImage;
	private Toast toast;
	public static boolean isProtectorActive;
	Inventory inventory;

	Player player;
	PlayerController playerController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_activity);
		initUI();
		isProtectorActive = false;

	}

	@Override
	protected void onResume() {
		healpotCount.setText(getResources().getString(R.string.item_count_text)
				+ inventory.getHealpotCount());
		uniballCount.setText(getResources().getString(R.string.item_count_text)
				+ inventory.getUniballCount());
		reviveCount.setText(getResources().getString(R.string.item_count_text)
				+ inventory.getReviveCount());
		protectorCount.setText(getResources().getString(
				R.string.item_count_text)
				+ inventory.getProtectorCount());
		super.onResume();
	}

	private void initUI() {

		player = playerController.getInstance();
		inventory = player.getInventory();
		money = (TextView) findViewById(R.id.money);
		money.setText(getResources().getString(R.string.money_text)
				+ player.getMoney());

		if (inventory != null) {

			// Healpot
			healpotImage = (ImageView) findViewById(R.id.healpot_image);
			healpotName = (TextView) findViewById(R.id.item_healpot);
			healpotCount = (TextView) findViewById(R.id.item_healpot_count);

			healpotImage.setImageResource(R.drawable.healpot);
			healpotCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getHealpotCount());

			// Uniball
			uniballImage = (ImageView) findViewById(R.id.uniball_image);
			uniballName = (TextView) findViewById(R.id.item_uniball);
			uniballCount = (TextView) findViewById(R.id.item_uniball_count);

			uniballImage.setImageResource(R.drawable.uniball);
			uniballCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getUniballCount());

			// Revive
			reviveImage = (ImageView) findViewById(R.id.revive_image);
			reviveName = (TextView) findViewById(R.id.item_revive);
			reviveCount = (TextView) findViewById(R.id.item_revive_count);

			reviveImage.setImageResource(R.drawable.uniball);
			reviveCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getReviveCount());

			// Protectors
			protectorImage = (ImageView) findViewById(R.id.protectors_image);
			protectorName = (TextView) findViewById(R.id.item_protector);
			protectorCount = (TextView) findViewById(R.id.item_protector_count);

			protectorImage.setImageResource(R.drawable.uniball);
			protectorCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getProtectorCount());

			// Use Item Button
			useItemButton = (Button) findViewById(R.id.use_item_button);
			useProtectorButton = (Button) findViewById(R.id.use_protector_button);
			setButtonsOnClick();

		}

	}

	private void setButtonsOnClick() {

		useItemButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent useItem = new Intent(InventoryActivity.this,
						InventoryUnimonSwipeActivity.class);
				startActivity(useItem);
			}
		});

		useProtectorButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(player.getInventory().protectorAvailable()){
					showToast(R.string.use_protector_toast);
					player.getInventory().reduceProtector();
					protectorCount.setText(getResources().getString(
							R.string.item_count_text)
							+ inventory.getProtectorCount());
					isProtectorActive = true;
				}
				else{
					showToast(R.string.protector_toast);
				}
			}
		});
	}
	
	private void showToast(int toastText) {
		int duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}
	
}
