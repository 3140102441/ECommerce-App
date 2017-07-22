package com.jarvis.mytaobao.discover;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jarvis.mytaobao.home.WareActivity;
import com.jarvis.mytaobaotest.R;
import com.javis.Adapter.Adapter_GridView;
import com.zxing.activity.CaptureActivity;

/**
 * ����������
 * @author http://yecaoly.taobao.com
 *
 */
public class Discover_F extends Fragment {
	private GridView my_gridView_life;
	private GridView my_gridView_app;
	private Adapter_GridView adapter_GridView_life;
	private Adapter_GridView adapter_GridView_app;
	//��Դ�ļ�
	private int[] pic_path_life={R.drawable.find_g_1,R.drawable.find_g_2,R.drawable.find_g_3,R.drawable.find_g_4};
	private int[] pic_path_app={R.drawable.find_g_5,R.drawable.find_g_6,R.drawable.find_g_7,R.drawable.find_g_8};
	
	private LinearLayout ll_ShaoYiShao;
	private LinearLayout ll_game;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	
    	View view=LayoutInflater.from(getActivity()).inflate(R.layout.dicover_f, null);
    	initView(view);
		return view;
	}




	private void initView(View view){
		ll_ShaoYiShao=(LinearLayout) view.findViewById(R.id.ll_dicover_shao);
		ll_ShaoYiShao.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), CaptureActivity.class);
				startActivity(intent);
			}
		});
		ll_game=(LinearLayout) view.findViewById(R.id.ll_dicover_game);
		ll_game.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
			}
		});
    	my_gridView_life=(GridView)view.findViewById(R.id.gridView_find_life);
    	my_gridView_life.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	adapter_GridView_life=new Adapter_GridView(getActivity(), pic_path_life);
    	my_gridView_life.setAdapter(adapter_GridView_life);
    	my_gridView_life.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(), WareActivity.class);
				startActivity(intent);
			}
		});
    	
    	my_gridView_app=(GridView)view.findViewById(R.id.gridView_find_app);
    	my_gridView_app.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	adapter_GridView_app=new Adapter_GridView(getActivity(), pic_path_app);
    	my_gridView_app.setAdapter(adapter_GridView_app);
    	my_gridView_app.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    				long arg3) {
    			Intent intent = new Intent(getActivity(), WareActivity.class);
				startActivity(intent);
    		}
    	});
    }

    
}
