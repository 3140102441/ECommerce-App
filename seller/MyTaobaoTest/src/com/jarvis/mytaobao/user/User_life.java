package com.jarvis.mytaobao.user;

import java.util.Random;

import com.jarvis.MyView.ScratchTextView;
import com.jarvis.mytaobaotest.R;


import android.app.Activity;
import android.os.Bundle;

/**
 * �ι��ֽ���
 * @author http://yecaoly.taobao.com
 *
 */
public class User_life extends Activity { 

	/**�ι������*/
	private ScratchTextView tv_Scratch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.user_life);
		tv_Scratch=(ScratchTextView) findViewById(R.id.tv_Scratch);
		tv_Scratch.initScratchCard(0xFFCECED1, 3, 1f);
		tv_Scratch.setText(str_reward[getRandom()]);
	}
	
	
	private String[] str_reward={"лл�ݹ�","��ϲ������5ë","������������","���㽱","���ý�","Srroy�������ɣ�","��ϲ��һ�Ƚ�","�ܱ�Ǹ","û�н�","����һƿ������"};
	
	
	/**�������һ����*/
	private int getRandom(){
		Random random=new Random();
		int number=random.nextInt(10);
		
		return number;
	}

}
