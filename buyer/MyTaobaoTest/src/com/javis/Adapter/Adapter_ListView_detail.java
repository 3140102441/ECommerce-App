package com.javis.Adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.jarvis.mytaobaotest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_ListView_detail extends BaseAdapter {
private Context context;
@SuppressWarnings("unused")
private ArrayList<HashMap<String, Object>> arrayList;
	
	@SuppressWarnings("unchecked")
	public Adapter_ListView_detail(Context context,HashMap<String, Object> hashmap){
		
		this.context=context;
		this.arrayList=(ArrayList<HashMap<String, Object>>) hashmap.get("data");
	}
	
	public Adapter_ListView_detail(Context context){
		this.context=context;
		
	}
	
	@Override
	public int getCount() {
		return 10;
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
	public View getView(int position, View currentView, ViewGroup arg2) {
		HolderView holderView=null;
		if (currentView==null) {
			holderView=new HolderView();
			currentView=LayoutInflater.from(context).inflate(R.layout.adapter_listview_detail, null);
			currentView.setTag(holderView);
		}else {
			holderView=(HolderView) currentView.getTag();
		}
		
		
		return currentView;
	}
	
	
	@SuppressWarnings("unused")
	public class HolderView {
		
		private ImageView iv_pic;
		private TextView tv_sale_num;
		private TextView tv_name;
		private TextView tv_price;
		
	}

}
