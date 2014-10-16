package de.ur.unimon.actionbar;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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

	private Button useItemButton, useProtectorButton, backToMapButton;
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
	public void onBackPressed() {

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

	@Override
	protected void onDestroy() {
		DatabaseController databaseController = new DatabaseController(this);
		databaseController.save();
		super.onDestroy();
	}

	private void initUI() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "PokemonFont.ttf");

		player = playerController.getInstance();
		inventory = player.getInventory();
		money = (TextView) findViewById(R.id.money);
		money.setText(getResources().getString(R.string.money_text)
				+ player.getMoney());
		money.setTypeface(font);

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

			reviveImage.setImageResource(R.drawable.revive);
			reviveCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getReviveCount());

			// Protectors
			protectorImage = (ImageView) findViewById(R.id.protectors_image);
			protectorName = (TextView) findViewById(R.id.item_protector);
			protectorCount = (TextView) findViewById(R.id.item_protector_count);

			protectorImage.setImageResource(R.drawable.protector);
			protectorCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getProtectorCount());

			// Use Item Button
			useItemButton = (Button) findViewById(R.id.use_item_button);
			useItemButton.setTypeface(font);
			useProtectorButton = (Button) findViewById(R.id.use_protector_button);
			useProtectorButton.setTypeface(font);
			backToMapButton = (Button) findViewById(R.id.back_to_map_inventory_button);
			backToMapButton.setTypeface(font);
			setButtonsOnClick();

		}

	}

	private void setButtonsOnClick() {

		useItemButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (player.getInventory().healpotAvailable()
						|| player.getInventory().reviveAvailable()) {
					Intent useItem = new Intent(InventoryActivity.this,
							InventoryUnimonSwipeActivity.class);
					startActivity(useItem);
				} else {
					showToast(R.string.no_items_available);
				}
			}
		});

		useProtectorButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (player.getInventory().protectorAvailable()) {
					showToast(R.string.use_protector_toast);
					player.getInventory().reduceProtector();
					protectorCount.setText(getResources().getString(
							R.string.item_count_text)
							+ inventory.getProtectorCount());
					isProtectorActive = true;
				} else {
					showToast(R.string.no_items_available);
				}
			}
		});

		backToMapButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent backToMap = new Intent(InventoryActivity.this,
						MapActivity.class);
				startActivity(backToMap);
				backToMap.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
	}

	private void showToast(int toastText) {
		int duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(this, toastText, duration);
		toast.show();
	}

}
