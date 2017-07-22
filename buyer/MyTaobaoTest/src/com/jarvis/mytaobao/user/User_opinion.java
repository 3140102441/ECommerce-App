package com.jarvis.mytaobao.user;

import java.io.UnsupportedEncodingException;

import com.jarvis.mytaobaotest.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.nfc.NdefRecord;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;

/**
 * �����������
 * @author http://yecaoly.taobao.com
 *
 */
public class User_opinion extends Activity implements OnClickListener { 

	private ImageView iv_back;
	private TextView nfc_info;
	 NfcAdapter mNfcAdapter;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_opinion);
		initView();
	}
	
	@SuppressLint("NewApi")
	private void initView(){
		
		iv_back=(ImageView) findViewById(R.id.iv_opinion_back);
		nfc_info = (TextView)findViewById(R.id.nfc_info);
		
		iv_back.setOnClickListener(this);
		 mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
	        if (mNfcAdapter == null) {
	            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
	            finish();
	            return;
	        }
	        if (!mNfcAdapter.isEnabled()) {
	            Toast.makeText(this, "����ϵͳ������������NFC���ܣ�", Toast.LENGTH_LONG).show();
	            finish();
	            return;
	        }	
	}
	
	
	 @Override
	    public void onResume() {
	        super.onResume();
	        // Check to see that the Activity started due to an Android Beam


	        enableForegroundDispatchSystem();
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();


	        disableForegroundDispatchSystem();
	    }
	    
	
	 @SuppressLint("NewApi")
	@Override
	    public void onNewIntent(Intent intent) {
	        // onResume gets called after this to handle the intent
	        super.onNewIntent(intent);

	      
	            Parcelable[] parcelables =  intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);               //��ȡ���ַ�����2���Ʊ���

	            if(parcelables != null && parcelables.length > 0 ){

	                readTextFromTag((NdefMessage)parcelables[0]);
	            }else{
	                Toast.makeText(this, "NO NDEF message found", Toast.LENGTH_SHORT).show();
	            }
	        

	    }
	
	 @SuppressLint("NewApi")
	private void readTextFromTag(NdefMessage ndefMessage) {

	        NdefRecord[] ndefRecords = ndefMessage.getRecords();

	        if(ndefRecords != null && ndefRecords.length > 0 ){
	            NdefRecord ndefRecord = ndefRecords[0];

	            String tagContent = getTextFromNdefRecord(ndefRecord);
	            
	            
	            if(tagContent.equals("1"))
	            	nfc_info.setText("��Ʒ����  ����ȹ\n\n��Ʒ����:  ��100.0\n\n��Ʒ����:  *1\n\n��Ʒ�ܼ�:  ��100.0\n\n������ַ:  �㽭��ѧ��ȪУ��30��\n\n�ͻ�����:  춺�\n\n�ͻ���ϵ�绰:  13676632069\n");                                                                     //��ȡ���ַ���
	        }else{
	            Toast.makeText(this, "NO NDEF record found", Toast.LENGTH_SHORT).show();
	        }
	    }

	 @SuppressLint("NewApi")
	public String getTextFromNdefRecord(NdefRecord ndefRecord){

	        String tagContent = null;
	        try{
	            byte[] payload = ndefRecord.getPayload();
	            String  textEndcoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
	            int languageSize  = payload[0] & 0063;
	            tagContent = new String(payload , languageSize + 1, payload.length - languageSize - 1, textEndcoding);
	        } catch (UnsupportedEncodingException e) {
	            Log.e("getTextFromNdefRecord", e.getMessage(), e);
	        }
	        return tagContent;
	    }
	 
	 
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_opinion_back:
			finish();
			break;
		
		default:
			break;
		}
	}
	





	 @SuppressLint("NewApi")
		private void enableForegroundDispatchSystem(){

		        Intent intent = new Intent(this, User_opinion.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

		        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, 0 );

		        IntentFilter[] intentFilter = new IntentFilter[]{};

		        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);

		    }
		    @SuppressLint("NewApi")
			private void  disableForegroundDispatchSystem(){

		        mNfcAdapter.disableForegroundDispatch(this);

		    }
	

}
