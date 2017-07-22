package com.jarvis.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttp {
	
	public static String RequstGetHttp(String strUrl) {
		URL url = null;
		String result="";
		try {
			url = new URL(strUrl);
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setConnectTimeout(1000);
			urlconn.setReadTimeout(15000);

			urlconn.connect();// ���ӷ�������������Ϣ


			// ��ʼ���շ��ص�����
			InputStreamReader is = new InputStreamReader(urlconn.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(is);
			String readLine = "";
			while ((readLine = bufferedReader.readLine()) != null) {
				result += readLine;
			}
			
			is.close();
			urlconn.disconnect();
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return result;
	}

}
