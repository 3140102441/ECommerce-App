package com.javis.Adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.jarvis.mytaobaotest.R;

public class Adapter_ListView_cart extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
	private onCheckedChanged listener;

	public Adapter_ListView_cart(Context context, ArrayList<HashMap<String, Object>> arrayList) {

		this.context = context;
		this.arrayList = arrayList;
	}

	public Adapter_ListView_cart(Context context) {
		this.context = context;

	}

	@Override
	public int getCount() {
		return (arrayList != null && arrayList.size() == 0) ? 0: arrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View currentView, ViewGroup arg2) {
		HolderView holderView = null;
		if (currentView == null) {
			holderView = new HolderView();
			currentView = LayoutInflater.from(context).inflate(R.layout.adapter_listview_cart, null);
			holderView.tv_num = (TextView) currentView.findViewById(R.id.tv_num);
			holderView.tv_type_color = (TextView) currentView.findViewById(R.id.tv_type_color);
			holderView.cb_choice = (CheckBox) currentView.findViewById(R.id.cb_choice);
			currentView.setTag(holderView);
		} else {
			holderView = (HolderView) currentView.getTag();
		}
		if (arrayList.size() != 0) {
			holderView.tv_num.setText("x" + arrayList.get(position).get("num"));
			holderView.tv_type_color.setText("¿‡–Õ:" + arrayList.get(position).get("type").toString() + "    —’…´:" + arrayList.get(position).get("color").toString());
			
			holderView.cb_choice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean choice) {
					listener.getChoiceData(position, choice);
				}
			});
		
		
		}
		return currentView;
	}

	public class HolderView {

		private TextView tv_type_color;
		private TextView tv_num;
		private CheckBox cb_choice;

	}
	
	public interface onCheckedChanged{
		
		public void getChoiceData(int position,boolean isChoice);
	}
	public void setOnCheckedChanged(onCheckedChanged listener){
		this.listener=listener;
	}

}
