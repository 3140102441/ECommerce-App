package com.jarvis.mytaobao.home;

import com.jarvis.mytaobaotest.R;
import com.lesogo.cu.custom.ScaleView.HackyViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 显示大图界面
 * @author http://yecaoly.taobao.com
 *
 */
public class ShowBigPictrue extends FragmentActivity {

	private HackyViewPager viewPager;
	private int[] resId={R.drawable.detail_show_1,R.drawable.detail_show_2,R.drawable.detail_show_3,R.drawable.detail_show_4,R.drawable.detail_show_5,R.drawable.detail_show_6};
	/**得到上一个界面点击图片的位置*/
	private int position=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_big_pictrue_a);
		Intent intent=getIntent();
		position=intent.getIntExtra("position", 0);
		
		initViewPager();
	}
	
private void initViewPager(){
		
		viewPager = (HackyViewPager) findViewById(R.id.viewPager_show_bigPic);
		ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		//跳转到第几个界面
		viewPager.setCurrentItem(position);
		
	}
	
	private class ViewPagerAdapter extends FragmentStatePagerAdapter{

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			int show_resId=resId[position];
			return new PictrueFragment(show_resId);
		}

		@Override
		public int getCount() {
			return resId.length;
		}

		
	}

}
