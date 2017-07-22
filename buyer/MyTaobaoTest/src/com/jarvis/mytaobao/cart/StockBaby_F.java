package com.jarvis.mytaobao.cart;

import com.jarvis.mytaobaotest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 购物车界面中的库存界面
 * @author http://yecaoly.taobao.com
 *
 */
public class StockBaby_F extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=LayoutInflater.from(getActivity()).inflate(R.layout.cart_stock_f, null);
		initView(view);
		return view;
	}
	
	private void initView(View view){
		
		
		
	}

}
