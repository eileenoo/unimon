package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.appstart.StartScreenActivity;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.start.newgame.NewGameActivity;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.unimons.Unimon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InventoryActivity extends Activity {

	private TextView money, healpotName, uniballName, reviveName,
			protectorName, healpotCount, uniballCount, reviveCount,
			protectorCount;
	private Button useItemButton, healpotImage, uniballImage, reviveImage,
			protectorImage;
	Inventory inventory;
	Player player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_activity);
		initUI();

	}

	private void initUI() {
		player = de.ur.unimon.appstart.StartScreenActivity.player;
		inventory = player.getInventory();
		money = (TextView) findViewById(R.id.money);
		money.setText(getResources().getString(R.string.money_text)
				+ player.getMoney());

		if (inventory != null) {

			// Healpot
			healpotImage = (Button) findViewById(R.id.healpot_image);
			healpotName = (TextView) findViewById(R.id.item_healpot);
			healpotCount = (TextView) findViewById(R.id.item_healpot_count);

			// healpotImage.setImageResource(R.drawable.ic_launcher);
			healpotCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getHealpotCount());

			// Uniball
			uniballImage = (Button) findViewById(R.id.uniball_image);
			uniballName = (TextView) findViewById(R.id.item_uniball);
			uniballCount = (TextView) findViewById(R.id.item_uniball_count);

			// uniballImage.setImageResource(R.drawable.ic_launcher);
			uniballCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getUniballCount());

			// Revive
			reviveImage = (Button) findViewById(R.id.revive_image);
			reviveName = (TextView) findViewById(R.id.item_revive);
			reviveCount = (TextView) findViewById(R.id.item_revive_count);

			// reviveImage.setImageResource(R.drawable.ic_launcher);
			reviveCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getReviveCount());

			// Protectors
			protectorImage = (Button) findViewById(R.id.protectors_image);
			protectorName = (TextView) findViewById(R.id.item_protector);
			protectorCount = (TextView) findViewById(R.id.item_protector_count);

			// protectorImage.setImageResource(R.drawable.ic_launcher);
			protectorCount.setText(getResources().getString(
					R.string.item_count_text)
					+ inventory.getProtectorCount());

			// Use Item Button
			useItemButton = (Button) findViewById(R.id.use_item_button);

			setButtonsOnClick();

		}

	}

	private void setButtonsOnClick() {
		useItemButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent useItem = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(useItem);
			}
		});}}
		/*healpotImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent addHealpot = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(addHealpot);
			}
		});

		uniballImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent addUniball = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(addUniball);
			}
		});

		reviveImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent addRevive = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(addRevive);
			}
		});

		protectorImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent addProtector = new Intent(InventoryActivity.this,
						InventoryUnimonListActivity.class);
				startActivity(addProtector);
			}
		});*/

		
