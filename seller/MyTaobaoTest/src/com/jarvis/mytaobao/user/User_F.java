package com.jarvis.mytaobao.user;

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
import android.widget.TextView;

import com.jarvis.mytaobaotest.R;
import com.javis.Adapter.Adapter_GridView;

/**
 * 我的淘宝主界面
 * @author http://yecaoly.taobao.com
 *
 */
public class User_F extends Fragment implements OnClickListener {
	private GridView my_gridView_user;
	private Adapter_GridView adapter_GridView;
	//资源文件
	private int[] pic_path={R.drawable.user_3,R.drawable.user_4,R.drawable.user_5,R.drawable.user_6,R.drawable.user_7};
    
	private LinearLayout ll_user_life;
	private LinearLayout ll_user_members;
	private LinearLayout ll_user_store;
	private LinearLayout ll_user_opinion;
    
    

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
    	
    	View view=LayoutInflater.from(getActivity()).inflate(R.layout.user_f, null);
    	initView(view);
		return view;
	}




	private void initView(View view){
    	
		((TextView)view.findViewById(R.id.tv_top_txtTitle)).setText("我的信息");
		ll_user_life=(LinearLayout) view.findViewById(R.id.ll_user_life);
		ll_user_members=(LinearLayout) view.findViewById(R.id.ll_user_members);
		ll_user_store=(LinearLayout) view.findViewById(R.id.ll_user_store);
		ll_user_opinion=(LinearLayout) view.findViewById(R.id.ll_user_opinion);
		ll_user_life.setOnClickListener(this);
		ll_user_members.setOnClickListener(this);
		ll_user_store.setOnClickListener(this);
		ll_user_opinion.setOnClickListener(this);
		
		
    	my_gridView_user=(GridView)view.findViewById(R.id.gridView_user);
    	my_gridView_user.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	adapter_GridView=new Adapter_GridView(getActivity(), pic_path);
    	my_gridView_user.setAdapter(adapter_GridView);
    	my_gridView_user.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//进入本机拥有传感器界面
				/*Intent intent=new Intent(getActivity(),HelloSensor.class);
				startActivity(intent);*/
				
			}
		});
    	
    	
    }




	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.ll_user_life:
			//进入刮刮乐界面
			Intent intent1=new Intent(getActivity(),User_life.class);
			startActivity(intent1);
			break;
		case R.id.ll_user_members:
			//进入刮刮乐界面
			Intent intent2=new Intent(getActivity(),User_life.class);
			startActivity(intent2);
			break;
		case R.id.ll_user_store:
			//进入刮刮乐界面
			Intent intent3=new Intent(getActivity(),User_life.class);
			startActivity(intent3);
			break;
		case R.id.ll_user_opinion:
			//意见反馈界面
			Intent intent4=new Intent(getActivity(),User_opinion.class);
			startActivity(intent4);
			break;

		default:
			break;
		}
		
	}

    
}
