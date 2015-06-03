package com.example.menurecipes;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListAdapter extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_SEPARATOR = 1;
	public static final int TYPE_ADD_ITEM = 2;

	ViewHolder holder = new ViewHolder();
	public LayoutInflater mInflater;

	public ListAdapter(Context context, ArrayList<Double> caloriesList) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		holder.caloriesList = caloriesList;
	}

	public void addItem(ArrayList list) {

		holder.mData.addAll(list);

		// Log.i("myLogsss","list = "+ list.toString());
		// Log.i("myLogsss","mData = "+ holder.mData.toString());

		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {

		int type;

		if (holder.mData.get(position).equals("Breakfast")) {
			holder.eatingName.put(position,
					"Breakfast" + holder.caloriesList.get(0));
			return TYPE_SEPARATOR;
		}

		if (holder.mData.get(position).equals("Lanch")) {
			holder.eatingName.put(position,
					"Lanch" + holder.caloriesList.get(1));
			return TYPE_SEPARATOR;
		}

		if (holder.mData.get(position).equals("Dinner")) {
			holder.eatingName.put(position,
					"Dinner" + holder.caloriesList.get(2));
			return TYPE_SEPARATOR;
		}

		if (holder.mData.get(position).equals("Supper")) {
			holder.eatingName.put(position,
					"Supper" + holder.caloriesList.get(3));
			return TYPE_SEPARATOR;
		}

		if (holder.mData.get(position).equals("add"))
			return TYPE_ADD_ITEM;

		if (holder.mData.get(position).toString().contains("NAME"))
			return TYPE_ITEM;

		else
			return -1;

	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public int getCount() {
		return holder.mData.size();
	}

	@Override
	public Dish getItem(int position) {
		if (!holder.mData.get(position).equals("separator")
				|| !holder.mData.get(position).equals("add"))
			return (Dish) holder.mData.get(position);

		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		int rowType = getItemViewType(position);
		Log.i("myLogsss", position + "");
		if (convertView == null) {

			switch (rowType) {
			case TYPE_ITEM:

				convertView = mInflater.inflate(R.layout.list_item, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.text);

				holder.imageView = (ImageView) convertView
						.findViewById(R.id.image_dish);

				holder.textView.setText(((Dish) holder.mData.get(position))
						.getName());

				holder.imageView.setImageDrawable(((Dish) holder.mData
						.get(position)).getDrawable());

				break;
			case TYPE_SEPARATOR:
				convertView = mInflater.inflate(R.layout.header_item, null);
				holder.textView2 = (TextView) convertView
						.findViewById(R.id.textSeparator);

				holder.textView2.setText(holder.mData.get(position) + "");
				break;
			case TYPE_ADD_ITEM:

				convertView = mInflater.inflate(R.layout.add_item, null);
				break;

			}
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		switch (rowType) {
		case TYPE_ITEM:
			Log.i("myLogsss", position + ""
					+ holder.mData.get(position).toString());
			holder.textView.setText(holder.mData.get(position).toString());

			holder.imageView.setImageDrawable(((Dish) holder.mData
					.get(position)).getDrawable());

			break;
		case TYPE_SEPARATOR:

			holder.textView2.setText(holder.mData.get(position) + "");

			break;
		case TYPE_ADD_ITEM:

			break;

		}

		return convertView;
	}

	public static class ViewHolder {
		public HashMap<Integer, String> eatingName = new HashMap<Integer, String>();
		public ArrayList<Double> caloriesList = new ArrayList<Double>();
		public ArrayList mData = new ArrayList<>();

		public TextView textView;
		public TextView textView2;
		public ImageView imageView;
		public ImageView imageView2;
	}

}
