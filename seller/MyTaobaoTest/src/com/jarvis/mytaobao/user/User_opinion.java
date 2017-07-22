package com.jarvis.mytaobao.user;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

import com.jarvis.mytaobaotest.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
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



/**
 * 意见反馈界面
 * @author http://yecaoly.taobao.com
 *
 */
public class User_opinion extends Activity implements OnClickListener { 

	private ImageView iv_back;
	private TextView nfc_info;
	
	 NfcAdapter mNfcAdapter;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_opinion);
		initView();
		
		
	        
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
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void initView(){
		
		iv_back=(ImageView) findViewById(R.id.iv_opinion_back);
		nfc_info=(TextView) findViewById(R.id.nfc_info);

		
		
		iv_back.setOnClickListener(this);
		 mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
	        if (mNfcAdapter == null) {
	            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
	            finish();
	            return;
	        }
	        if (!mNfcAdapter.isEnabled()) {
	            Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_LONG).show();
	            finish();
	            return;
	        }	
	}
	
	 @SuppressLint("NewApi")
	@Override
	    public void onNewIntent(Intent intent) {
	        // onResume gets called after this to handle the intent
	        super.onNewIntent(intent);
	        
	        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage ndefMessage =  createNdefMessage("1");                    //txtTag.getText是要输入的字符串

            writeNdefMessage(tag, ndefMessage);
           
	    }
	
	 @SuppressLint("NewApi")
	private void readTextFromTag(NdefMessage ndefMessage) {

	        NdefRecord[] ndefRecords = ndefMessage.getRecords();

	        if(ndefRecords != null && ndefRecords.length > 0 ){
	            NdefRecord ndefRecord = ndefRecords[0];

	            String tagContent = getTextFromNdefRecord(ndefRecord);

	            nfc_info.setText(tagContent);                                                                     //读取的字符串
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
	private NdefMessage createNdefMessage(String content) {
        NdefRecord ndefRecord = createTextRecord(content);

        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ ndefRecord});
        return ndefMessage;
    }
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public NdefRecord createTextRecord(String text) {  
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(  
                Charset.forName("US-ASCII"));  
        Charset utfEncoding = Charset.forName("UTF-8");  
        byte[] textBytes = text.getBytes(utfEncoding);  
        int utfBit = 0;  
        char status = (char) (utfBit + langBytes.length);  
  
        byte[] data = new byte[1 + langBytes.length + textBytes.length];  
        data[0] = (byte) status;  
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);  
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length,  
                textBytes.length);  
  
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,  
                NdefRecord.RTD_TEXT, new byte[0], data);  
        return ndefRecord;  
    }  
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@SuppressLint("NewApi")
	private void formatTag(Tag tag,NdefMessage ndefmessage) {
        try {
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if (ndefFormatable == null) {
                Toast.makeText(this, "Tag is not ndef format", Toast.LENGTH_SHORT).show();
            }


            ndefFormatable.connect();
            ndefFormatable.format(ndefmessage);
            ndefFormatable.close();
        } catch (Exception  e){
            Log.d("formatage", e.getMessage());
        }
    }
	
	 @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@SuppressLint("NewApi")
	private void writeNdefMessage(Tag tag, NdefMessage ndefMessage){
	        try{
	            if (tag == null){
	                Toast.makeText(this, "tag object cannot be null", Toast.LENGTH_SHORT).show();
	                return;
	            }
	            Ndef ndef = Ndef.get(tag);

	            if (ndef == null){
	                formatTag(tag, ndefMessage);
	            }
	            else{

	                ndef.connect();
	                if(!ndef.isWritable()){
	                    Toast.makeText(this, "tag is not writable", Toast.LENGTH_SHORT).show();
	                    ndef.close();
	                    return;
	                }
	                
	                ndef.writeNdefMessage(ndefMessage);
	                ndef.close();
	                Toast.makeText(this, "账单信息已经写入nfc芯片", Toast.LENGTH_LONG).show();

	            }
	        }catch (Exception e){
	            Log.d("write message", e.getMessage());
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
