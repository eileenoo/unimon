package de.ur.unimon.buildings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.battle.BattleEndActivity;
import de.ur.unimon.database.DatabaseController;
import de.ur.unimon.mapoverview.MapActivity;
import de.ur.unimon.player.Player;
import de.ur.unimon.player.PlayerController;

public class ShopActivity extends Activity {

	private TextView money, healpotName, uniballName, reviveName,
			protectorName, healpotPrice, uniballPrice, revivePrice,
			protectorPrice, healpotCount, uniballCount, reviveCount,
			protectorCount;
	private ImageView healpotImage, uniballImage, reviveImage, protectorImage;
	private int currentMoney, healpot_Price, uniball_Price, revive_Price,
			protector_Price;
	ImageButton buyHealpotButton, buyUniballButton, buyReviveButton, buyProtectorButton;
	Button leaveShopButton;

	Inventory inventory;
	private Player player;
	private PlayerController playerController;
	AlertDialog.Builder builder;
	Toast toast;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_activity);
		player = playerController.getInstance();
		builder = new AlertDialog.Builder(this);
		initUI();
	}
	
	@Override
	public void onBackPressed() {
	    
	}
	
	@Override
	protected void onDestroy() {
		DatabaseController databaseController = new DatabaseController(this);
		databaseController.save();
		super.onDestroy();
	}
	
	private void initUI() {
		Typeface font = Typeface.createFromAsset(getAssets(), "PokemonFont.ttf");
		
		inventory = player.getInventory();
		money = (TextView) findViewById(R.id.money);
		money.setText(getResources().getString(R.string.money_text) + player.getMoney());
		money.setTypeface(font);
		
		// Healpot
		healpot_Price = 50;
		healpotImage = (ImageView) findViewById(R.id.healpot_image);
		healpotName = (TextView) findViewById(R.id.item_healpot);
		healpotCount = (TextView) findViewById(R.id.item_healpot_count);
		healpotPrice = (TextView) findViewById(R.id.item_healpot_price);

		healpotImage.setImageResource(R.drawable.healpot);
		healpotCount.setText(getResources().getString(R.string.item_count_text) + inventory.getHealpotCount());
		healpotPrice.setText("" + healpot_Price);
		healpotPrice.setTypeface(font);
		
		buyHealpotButton = (ImageButton) findViewById(R.id.buy_healpot_button);

		// Uniball
		uniball_Price = 250;
		uniballImage = (ImageView) findViewById(R.id.uniball_image);
		uniballName = (TextView) findViewById(R.id.item_uniball);
		uniballCount = (TextView) findViewById(R.id.item_uniball_count);
		uniballPrice = (TextView) findViewById(R.id.item_uniball_price);

		uniballImage.setImageResource(R.drawable.uniball);
		uniballCount.setText(getResources().getString(R.string.item_count_text) + inventory.getUniballCount());
		uniballPrice.setText("" + uniball_Price);
		uniballPrice.setTypeface(font);
		
		buyUniballButton = (ImageButton) findViewById(R.id.buy_uniball_button);

		// Revive
		revive_Price = 200;
		reviveImage = (ImageView) findViewById(R.id.revive_image);
		reviveName = (TextView) findViewById(R.id.item_revive);
		reviveCount = (TextView) findViewById(R.id.item_revive_count);
		revivePrice = (TextView) findViewById(R.id.item_revive_price);
		revivePrice.setTypeface(font);

		reviveImage.setImageResource(R.drawable.revive);
		reviveCount.setText(getResources().getString(R.string.item_count_text) + inventory.getReviveCount());
		revivePrice.setText("" + revive_Price);
		
		buyReviveButton = (ImageButton) findViewById(R.id.buy_revive_button);		

		// Protectors
		protector_Price = 300;
		protectorImage = (ImageView) findViewById(R.id.protectors_image);
		protectorName = (TextView) findViewById(R.id.item_protector);
		protectorCount = (TextView) findViewById(R.id.item_protector_count);
		protectorPrice = (TextView) findViewById(R.id.item_protector_price);

		protectorImage.setImageResource(R.drawable.protector);
		protectorCount.setText(getResources().getString(R.string.item_count_text) + inventory.getProtectorCount());
		protectorPrice.setText("" + protector_Price);
		protectorPrice.setTypeface(font);
		
		buyProtectorButton = (ImageButton) findViewById(R.id.buy_protector_button);	
		
		leaveShopButton = (Button) findViewById(R.id.leave_shop_button);
		leaveShopButton.setTypeface(font);
		
		setButtonsOnClick();

	}

	private void setButtonsOnClick() {
		
		leaveShopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent backToMap = new Intent (ShopActivity.this, MapActivity.class);
				startActivity(backToMap);
				backToMap.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			}
		});
		
		
		buyHealpotButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= healpot_Price) {
					builder.setTitle(getResources().getString(R.string.healpot_name));
					builder.setMessage("Do you really want to buy a Healpot?");

					builder.setPositiveButton(getResources().getString(R.string.ok),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									inventory.addHealpot();
									player.addMoney(-healpot_Price);
									money.setText(getResources().getString(R.string.money_text) + player.getMoney());
									healpotCount.setText(getResources().getString(R.string.item_count_text)
											+ inventory.getHealpotCount());
									dialog.dismiss();
								}
							});
					builder.setNegativeButton(getResources().getString(R.string.cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {									
									dialog.dismiss();
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					showLessMoneyAlert();
				}
			}
		});

		buyUniballButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= uniball_Price) {
					builder.setTitle(getResources().getString(R.string.uniball_name));
					builder.setMessage("Do you really want to buy a Uniball?");

					builder.setPositiveButton(getResources().getString(R.string.ok),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									inventory.addUniball();
									player.addMoney(-uniball_Price);
									money.setText(getResources().getString(R.string.money_text) + player.getMoney());
									uniballCount.setText(getResources().getString(R.string.item_count_text)
											+ inventory.getUniballCount());
								}
							});
					builder.setNegativeButton(getResources().getString(R.string.cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					showLessMoneyAlert();
				}
			}
		});

		buyReviveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= revive_Price) {
					builder.setTitle(getResources().getString(R.string.revive_name));
					builder.setMessage("Do you really want to buy a Revive?");

					builder.setPositiveButton(getResources().getString(R.string.ok),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									inventory.addRevive();
									player.addMoney(-revive_Price);
									money.setText(getResources().getString(R.string.money_text) + player.getMoney());
									reviveCount.setText(getResources().getString(R.string.item_count_text)
											+ inventory.getReviveCount());
								}
							});
					builder.setNegativeButton(getResources().getString(R.string.cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					showLessMoneyAlert();
				}
			}
		});

		buyProtectorButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= protector_Price) {
					builder.setTitle(getResources().getString(R.string.protector_name));
					builder.setMessage("Do you really want to buy a Protector?");

					builder.setPositiveButton(getResources().getString(R.string.ok),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									inventory.addProtector();
									player.addMoney(-protector_Price);
									money.setText(getResources().getString(R.string.money_text) + player.getMoney());
									protectorCount.setText(getResources().getString(R.string.item_count_text)
											+ inventory.getProtectorCount());
								}
							});
					builder.setNegativeButton(getResources().getString(R.string.cancel),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					showLessMoneyAlert();
				}
			}
		});

	}

	private void showLessMoneyAlert() {
		builder = new AlertDialog.Builder(this);
		builder.setTitle(getResources().getString(R.string.alert_money_warning_title));
		builder.setMessage(getResources().getString(R.string.alert_money_warning_message));
		builder.setPositiveButton(getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

}
