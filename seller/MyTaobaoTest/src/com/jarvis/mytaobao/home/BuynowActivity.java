package com.jarvis.mytaobao.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvis.mytaobaotest.R;



/**
 * ȷ�϶�������
 * @author http://yecaoly.taobao.com
 *
 */
public class BuynowActivity extends Activity {

	private TextView bt_ok,bt_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_now_a);
		initView();
		
	}
	
	private void initView(){
		bt_back=(TextView) findViewById(R.id.bt_buy_back);
		bt_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		bt_ok=(TextView) findViewById(R.id.tv_buy_ok);
		bt_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Toast.makeText(BuynowActivity.this, "���޷�֧��", Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

}
