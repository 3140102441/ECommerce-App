package com.jarvis.mytaobao.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
				   new AlertDialog.Builder(BuynowActivity.this)  
				   
				     .setMessage("确认支付吗？")//设置显示的内容  
				  
				     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
				  
				             // TODO Auto-generated method stub  
				  
				        	   new AlertDialog.Builder(BuynowActivity.this) 
				        	   
				        	     .setMessage("支付成功")//设置显示的内容  
				        	  
				        	     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
				        	  
				        	          
				        	  
				        	         @Override  
				        	  
				        	         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
				        	  
				        	             // TODO Auto-generated method stub  
				        	  
				        	             finish();  
				        	  
				        	         }  
				        	  
				        	     }
				        	  
				        	          
				        	  
				        	        
				        	  
				        	     ).show();//在按键响应事件中显示此对话框  
				  
				         }  
				  
				     }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//响应事件  
				  
				             // TODO Auto-generated method stub  
				  
				             Log.i("alertdialog"," 请保存数据！");  
				  
				         }  
				  
				     }).show();//在按键响应事件中显示此对话框  
			}
		});
		
		
	}

}
