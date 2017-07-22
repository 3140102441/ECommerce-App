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

public class Adapter_ListView_user extends BaseAdapter {
private Context context;
@SuppressWarnings("unused")
private ArrayList<HashMap<String, Object>> arrayList;
	
	@SuppressWarnings("unchecked")
	public Adapter_ListView_user(Context context,HashMap<String, Object> hashmap){
		
		this.context=context;
		this.arrayList=(ArrayList<HashMap<String, Object>>) hashmap.get("data");
	}
	
	public Adapter_ListView_user(Context context){
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
//			holderView.iv_pic=(ImageView) currentView.findViewById(R.id.iv_adapter_list_pic);
//			holderView.tv_name=(TextView) currentView.findViewById(R.id.name);
//			holderView.tv_price=(TextView) currentView.findViewById(R.id.price);
//			holderView.tv_sale_num=(TextView) currentView.findViewById(R.id.sale_num);
			currentView.setTag(holderView);
		}else {
			holderView=(HolderView) currentView.getTag();
		}
		
//		ImageListener listener= ImageLoader.getImageListener(holderView.iv_pic, R.drawable.ic_launcher,  R.drawable.ic_launcher);
//		CU_VolleyTool.getInstance(context).getmImageLoader().get("http://192.168.0.111:3000/taoBao/img/"+arrayList.get(position).get("pic"), listener);
//		
//		
//		holderView.tv_name.setText(arrayList.get(position).get("name").toString());
//		holderView.tv_price.setText("￥"+arrayList.get(position).get("price").toString()+"元");
//		holderView.tv_sale_num.setText("月销量:"+arrayList.get(position).get("sale_num").toString()+"件     "+arrayList.get(position).get("address").toString());
		
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
