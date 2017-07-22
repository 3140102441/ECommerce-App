package com.jarvis.mytaobao.home;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jarvis.MyView.BabyPopWindow;
import com.jarvis.MyView.BabyPopWindow.OnItemClickListener;
import com.jarvis.mytaobaotest.R;
import com.javis.Adapter.Adapter_ListView_detail;
import com.lesogo.cu.custom.ScaleView.HackyViewPager;


/**
 * ������Ʒ�������
 * @author http://yecaoly.taobao.com
 * 
 */
public class BabyActivity extends FragmentActivity implements OnItemClickListener, OnClickListener {

	NfcAdapter nfcAdapter;

	private HackyViewPager viewPager;
	private ArrayList<View> allListView;
	private int[] resId = { R.drawable.detail_show_1, R.drawable.detail_show_2, R.drawable.detail_show_3, R.drawable.detail_show_4, R.drawable.detail_show_5, R.drawable.detail_show_6 };
	private ListView listView;
	private ImageView iv_baby_collection;
	/**������Ʒ������Ϣ����*/
	private BabyPopWindow popWindow;
	/** �������ñ������� */
	private LinearLayout all_choice_layout = null;
	/**�ж��Ƿ�������������ť*/
	boolean isClickBuy = false;
	/**�Ƿ�����ղ�*/
	private static boolean isCollection=false; 
	/**ViewPager��ǰ��ʾҳ���±�*/
	private int position=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.babydetail_a);
		//�õ�������ղ���Ϣ
		getSaveCollection();
		initView();
		popWindow = new BabyPopWindow(this);
		popWindow.setOnItemClickListener(this);
	}

	@SuppressLint("NewApi")
	private void initView() {
		// ��ȡĬ�ϵ�NFC������
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null) {
			//Toast.makeText(this, "���豸��֧��NFC", Toast.LENGTH_SHORT).show();
		}
		
		((ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);
		((ImageView) findViewById(R.id.put_in)).setOnClickListener(this);
		((ImageView) findViewById(R.id.buy_now)).setOnClickListener(this);
		iv_baby_collection=(ImageView) findViewById(R.id.iv_baby_collection);
		iv_baby_collection.setOnClickListener(this);
		all_choice_layout = (LinearLayout) findViewById(R.id.all_choice_layout);
		listView = (ListView) findViewById(R.id.listView_Detail);
		listView.setFocusable(false);
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listView.setAdapter(new Adapter_ListView_detail(this));
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//��������èŮ�·�������
				Uri uri = Uri.parse("http://yecaoly.taobao.com"); 
				Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
				startActivity(intent);
			}
		});
		initViewPager();
		
		if (isCollection) {
			//����Ѿ��ղأ�����ʾ�ղغ��Ч��
			iv_baby_collection.setImageResource(R.drawable.second_2_collection);
		}
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back:
			//����
			finish();
			break;
		case R.id.iv_baby_collection:
			//�ղ�
			if (isCollection) {
				//��ʾ�Ƿ�ȡ���ղ�
				cancelCollection();
			}else {
				isCollection=true;
				setSaveCollection();
				//����Ѿ��ղأ�����ʾ�ղغ��Ч��
				iv_baby_collection.setImageResource(R.drawable.second_2_collection);
				//Toast.makeText(this, "�ղسɹ�", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.put_in:
			//��ӹ��ﳵ
			isClickBuy = false;
			setBackgroundBlack(all_choice_layout, 0);
			popWindow.showAsDropDown(view);
			break;
		case R.id.buy_now:
			//��������
			isClickBuy = true;
			setBackgroundBlack(all_choice_layout, 0);
			popWindow.showAsDropDown(view);
			break;
		}
	}
	
	

	private void initViewPager() {

		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < resId.length; i++) {
			View view = LayoutInflater.from(this).inflate(R.layout.pic_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			imageView.setImageResource(resId[i]);
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					//��ս���鿴��ͼ����
					Intent intent = new Intent(BabyActivity.this, ShowBigPictrue.class);
					intent.putExtra("position", position);
					startActivity(intent);
				}
			});
			allListView.add(view);
		}

		viewPager = (HackyViewPager) findViewById(R.id.iv_baby);
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				position=arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		viewPager.setAdapter(adapter);

	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return allListView.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (View) arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = allListView.get(position);
			container.addView(view);
			return view;
		}

	}

	//���������ȷ�ϰ�ť����Ӧ
	@Override
	public void onClickOKPop() {
		setBackgroundBlack(all_choice_layout, 1);

		if (isClickBuy) {
			//���֮ǰ�ǵ��������������ô����ת�������������
			Intent intent = new Intent(BabyActivity.this, BuynowActivity.class);
			startActivity(intent);
		}else {
			//Toast.makeText(this, "��ӵ����ﳵ�ɹ�", Toast.LENGTH_SHORT).show();
		}
	}

	/** ���Ʊ����䰵 0�䰵 1���� */
	public void setBackgroundBlack(View view, int what) {
		switch (what) {
		case 0:
			view.setVisibility(View.VISIBLE);
			break;
		case 1:
			view.setVisibility(View.GONE);
			break;
		}
	}

	/**�����Ƿ�����ղ�*/
	private void setSaveCollection(){
		SharedPreferences sp=getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putBoolean("isCollection", isCollection);
		editor.commit();
	}
	/**�õ�������Ƿ�����ղر��*/
	private void getSaveCollection(){
		SharedPreferences sp=getSharedPreferences("SAVECOLLECTION", Context.MODE_PRIVATE);
		isCollection=sp.getBoolean("isCollection", false);
		
	}
	/**ȡ���ղ�*/
	private  void cancelCollection(){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("�Ƿ�ȡ���ղ�");
		dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				isCollection=false;
				//���ȡ���ղأ�����ʾȡ���ղغ��Ч��
				iv_baby_collection.setImageResource(R.drawable.second_2);
				setSaveCollection();
			}
		});
		dialog.setNegativeButton("ȡ��", null);
		dialog.create().show();
		
	}

}
