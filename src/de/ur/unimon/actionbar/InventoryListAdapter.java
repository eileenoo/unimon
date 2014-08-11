package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
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
//		ArrayList<Inventory> inventory;
		private TextView inventoryName, inventoryCount;
		private Button inventoryImage;

	/*	public LocationListAdapter(Context context,
				ArrayList<Inventory> inventory) {
			this.context = context;
			infalInflater = LayoutInflater.from(context);
			this.inventory = inventory;
		}*/

		@Override
		public int getCount() {
			return 0; //inventory.size();
		}

		@Override
		public Object getItem(int position) {
			return null; //inventory.get(position);
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
			
		//	Inventory inventory = (Inventory) getItem(position);
			
		//	if (inventory != null){
				inventoryImage = (Button) convertView.findViewById(R.id.item_image);
				inventoryName = (TextView) convertView.findViewById(R.id.item_name);
		//		inventoryCount = (TextView) convertView.findViewById(R.id.unimon_level);
				
		//		inventoryImage.setImageResource(R.drawable.ic_launcher);		
		//		inventoryName.setText("" + inventory.getName());
		//		inventoryCount.setText("" + inventory.getCount());
		//	}
			return convertView;

		}

	


}
