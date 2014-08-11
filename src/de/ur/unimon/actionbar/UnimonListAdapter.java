package de.ur.unimon.actionbar;

import java.util.ArrayList;

import de.ur.mi.android.excercises.starter.R;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UnimonListAdapter extends BaseAdapter{

	private Context context;
	LayoutInflater infalInflater;
//	ArrayList<Unimon> unimons;
	private TextView unimonName, unimonLevel;
	private Button unimonImage;

/*	public LocationListAdapter(Context context,
			ArrayList<Unimon> unimons) {
		this.context = context;
		infalInflater = LayoutInflater.from(context);
		this.unimons = unimons;
	}*/

	@Override
	public int getCount() {
		return 0; //unimons.size();
	}

	@Override
	public Object getItem(int position) {
		return null; //unimons.get(position);
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
		
	//	Unimon unimon = (Unimon) getItem(position);
		
	//	if (unimon != null){
			unimonImage = (Button) convertView.findViewById(R.id.unimon_image);
			unimonName = (TextView) convertView.findViewById(R.id.unimon_name);
			unimonLevel = (TextView) convertView.findViewById(R.id.unimon_level);
			
	//		unimonImage.setImageResource(R.drawable.ic_launcher);		
	//		unimonName.setText("" + unimon.getName());
	//		unimonLevel.setText("" + unimon.getLevel());
	//	}
		return convertView;

	}

}
