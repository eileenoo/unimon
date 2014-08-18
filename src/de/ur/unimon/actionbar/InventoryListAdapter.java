package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import de.ur.unimon.unimons.Unimon;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InventoryListAdapter extends BaseAdapter{
	

		private Context context;
		LayoutInflater infalInflater;
		ArrayList<Inventory> inventory;
		private TextView healpotName, uniballName, reviveName, protectorsName, healpotCount, uniballCount, reviveCount, protectorsCount;
		private Button healpotImage, uniballImage, reviveImage, protectorsImage;

		public InventoryListAdapter(Context context, ArrayList<Inventory> inventory) {
			this.context = context;
			infalInflater = LayoutInflater.from(context);
			this.inventory = inventory;
		}

		@Override
		public int getCount() {
			return inventory.size();
		}

		@Override
		public Object getItem(int position) {
			return inventory.get(position);
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
				convertView = infalInflater.inflate(R.layout.unimon_list_item,
						null);
				
			}
			
		Inventory inventory = (Inventory) getItem(position);
			
			if (inventory != null){
				
				//Healpot
				healpotImage = (Button) convertView.findViewById(R.id.healpot_image);
				healpotName = (TextView) convertView.findViewById(R.id.item_healpot);
				healpotCount = (TextView) convertView.findViewById(R.id.item_healpot_count);
				
				//	healpotImage.setImageResource(R.drawable.ic_launcher);		
				healpotCount.setText("Anzahl: " + inventory.getHealpotCount());
				
				//Uniball
				uniballImage = (Button) convertView.findViewById(R.id.uniball_image);
				uniballName = (TextView) convertView.findViewById(R.id.item_uniball);
				uniballCount = (TextView) convertView.findViewById(R.id.item_uniball_count);
				
				//	uniballImage.setImageResource(R.drawable.ic_launcher);		
				uniballCount.setText("Anzahl: " + inventory.getUniballCount());
				
				//Revive
				reviveImage = (Button) convertView.findViewById(R.id.revive_image);
				reviveName = (TextView) convertView.findViewById(R.id.item_revive);
				reviveCount = (TextView) convertView.findViewById(R.id.item_revive_count);
				
				//	reviveImage.setImageResource(R.drawable.ic_launcher);		
				reviveCount.setText("Anzahl: " + inventory.getReviveCount());
				
				//Protectors
				protectorsImage = (Button) convertView.findViewById(R.id.protectors_image);
				protectorsName = (TextView) convertView.findViewById(R.id.item_protector);
				protectorsCount = (TextView) convertView.findViewById(R.id.item_protector_count);
				
				//	protectorsImage.setImageResource(R.drawable.ic_launcher);		
				protectorsCount.setText("Anzahl: " + inventory.getProtectorCount());
				
			}
			return convertView;

		}

	


}
