package de.ur.unimon.buildings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.actionbar.Inventory;
import de.ur.unimon.startgame_logic.Player;
import de.ur.unimon.startgame_logic.PlayerController;

public class ShopActivity extends Activity {

	private TextView money, healpotName, uniballName, reviveName,
			protectorName, healpotPrice, uniballPrice, revivePrice,
			protectorPrice, healpotCount, uniballCount, reviveCount,
			protectorCount;
	private Button healpotImage, uniballImage, reviveImage, protectorImage;
	private int currentMoney, healpot_Price, uniball_Price, revive_Price,
			protector_Price;

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

	private void initUI() {
		inventory = player.getInventory();
		money = (TextView) findViewById(R.id.money);
		money.setText(getResources().getString(R.string.money_text) + player.getMoney() + "€");

		// Healpot
		healpot_Price = 100;
		healpotImage = (Button) findViewById(R.id.healpot_image);
		healpotName = (TextView) findViewById(R.id.item_healpot);
		healpotCount = (TextView) findViewById(R.id.item_healpot_count);
		healpotPrice = (TextView) findViewById(R.id.item_healpot_price);

		// healpotImage.setImageResource(R.drawable.ic_launcher);
		healpotCount.setText(getResources().getString(R.string.item_count_text) + inventory.getHealpotCount());
		healpotPrice.setText("" + healpot_Price + "€");

		// Uniball
		uniball_Price = 50;
		uniballImage = (Button) findViewById(R.id.uniball_image);
		uniballName = (TextView) findViewById(R.id.item_uniball);
		uniballCount = (TextView) findViewById(R.id.item_uniball_count);
		uniballPrice = (TextView) findViewById(R.id.item_uniball_price);

		// uniballImage.setImageResource(R.drawable.ic_launcher);
		uniballCount.setText(getResources().getString(R.string.item_count_text) + inventory.getUniballCount());
		uniballPrice.setText("" + uniball_Price + "€");

		// Revive
		revive_Price = 400;
		reviveImage = (Button) findViewById(R.id.revive_image);
		reviveName = (TextView) findViewById(R.id.item_revive);
		reviveCount = (TextView) findViewById(R.id.item_revive_count);
		revivePrice = (TextView) findViewById(R.id.item_revive_price);

		// reviveImage.setImageResource(R.drawable.ic_launcher);
		reviveCount.setText(getResources().getString(R.string.item_count_text) + inventory.getReviveCount());
		revivePrice.setText("" + revive_Price + "€");

		// Protectors
		protector_Price = 850;
		protectorImage = (Button) findViewById(R.id.protectors_image);
		protectorName = (TextView) findViewById(R.id.item_protector);
		protectorCount = (TextView) findViewById(R.id.item_protector_count);
		protectorPrice = (TextView) findViewById(R.id.item_protector_price);

		// protectorImage.setImageResource(R.drawable.ic_launcher);
		protectorCount.setText(getResources().getString(R.string.item_count_text) + inventory.getProtectorCount());
		protectorPrice.setText("" + protector_Price + "€");

		setButtonsOnClick();

	}

	private void setButtonsOnClick() {
		healpotImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= healpot_Price) {
					builder.setTitle(getResources().getString(R.string.healpot_name));
					builder.setMessage("Möchtest du Healpot wirklich kaufen?");

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

		uniballImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= uniball_Price) {
					builder.setTitle(getResources().getString(R.string.uniball_name));
					builder.setMessage("Möchtest du Uniball wirklich kaufen?");

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

		reviveImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= revive_Price) {
					builder.setTitle(getResources().getString(R.string.revive_name));
					builder.setMessage("Möchtest du Revive wirklich kaufen?");

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

		protectorImage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				currentMoney = player.getMoney();
				if (currentMoney >= protector_Price) {
					builder.setTitle(getResources().getString(R.string.protector_name));
					builder.setMessage("Möchtest du Protector wirklich kaufen?");

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
