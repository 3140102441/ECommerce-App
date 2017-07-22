package com.javis.Adapter;


import com.jarvis.mytaobaotest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adapter_ListView_tao extends BaseAdapter {
	private Context context;
	/**ͼƬ*/
	private int[] picId={R.drawable.tao_3,R.drawable.detail_show_1,R.drawable.detail_show_2,R.drawable.detail_show_3,R.drawable.detail_show_4,R.drawable.detail_show_5,R.drawable.detail_show_6};
	
	
	public Adapter_ListView_tao(Context context){
		this.context=context;
		
	}
	
	@Override
	public int getCount() {
		return picId.length;
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
			currentView=LayoutInflater.from(context).inflate(R.layout.adapter_listview_tao, null);
			holderView.iv_pic=(ImageView) currentView.findViewById(R.id.iv_adapter_list_pic);
			
			currentView.setTag(holderView);
		}else {
			holderView=(HolderView) currentView.getTag();
		}
		
		holderView.iv_pic.setImageResource(picId[position]);
		
		return currentView;
	}
	
	
	public class HolderView {
		private ImageView iv_pic;
	}

}
