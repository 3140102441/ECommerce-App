package com.javis.Adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class myViewPager extends PagerAdapter {

	private ArrayList<View> data;
	
	public myViewPager( ArrayList<View> data){
		this.data=data;
		
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==(View)arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view=data.get(position);
		container.addView(view);
		return view;
		
	}

	
	
	
}
