package com.jarvis.http;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Common Use JSON Resolve
 * @author ͨ��JSON������
 * @author ���������������Ϊ3��
 * @author ���������������
 */
public class CU_JSONResolve {
	/**
	 * ��ȡJSONObject��key��String���͵�value
	 * 
	 * @param jO
	 * @param key
	 * @return String_value
	 */
	private static String getSTR(JSONObject jO, String key) {

		String value = "";

		if (key.equals(""))
			return value;

		try {
			if (jO.has(key))
				value = jO.getString(key);
		} catch (JSONException e) {
			Log.v("gyygyygyy------>" + key, "gyygyygyy------>CU_JSONResolveException" + e.toString());
		}

		return value.equals("null") ? "" : value;
	}

	/**
	 * �ڲ�����1��JSON����
	 * 
	 * @param jO
	 * @param STR_field
	 * @return hashMap
	 */
	private static HashMap<String, Object> getHashMap1(JSONObject jO, String STR_field[]) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		if (STR_field == null)
			return hashMap;

		if (STR_field != null && STR_field.length != 0)
			for (int i = 0; i < STR_field.length; i++)
				hashMap.put(STR_field[i], getSTR(jO, STR_field[i]));

		return hashMap;
	}

	/**
	 * �ڲ�����2��JSON����
	 * 
	 * @param jO
	 * @param STR1_field
	 * @param LIST1_field
	 * @param aL_STR2_field
	 * @return
	 */
	private static HashMap<String, Object> getHashMap2(JSONObject jO, String STR1_field[], String LIST1_field[], ArrayList<String[]> aL_STR2_field) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		if (STR1_field == null && LIST1_field == null)
			return hashMap;

		if (STR1_field != null && STR1_field.length != 0)
			for (int i = 0; i < STR1_field.length; i++)
				hashMap.put(STR1_field[i], getSTR(jO, STR1_field[i]));

		if (LIST1_field != null && LIST1_field.length != 0)
			if (aL_STR2_field != null && aL_STR2_field.size() == LIST1_field.length)
				for (int i = 0; i < LIST1_field.length; i++)
					if (aL_STR2_field.get(i) != null && aL_STR2_field.get(i).length != 0)
						hashMap.put(LIST1_field[i], getLIST1(jO, LIST1_field[i], aL_STR2_field.get(i)));
		return hashMap;
	}

	/**
	 * ��ȡJSONObject��key��1��LIST���͵�value
	 * 
	 * @param jO
	 * @param key
	 * @param STR_field
	 * @return List_value
	 */
	private static ArrayList<HashMap<String, Object>> getLIST1(JSONObject jO, String key, String STR_field[]) {

		ArrayList<HashMap<String, Object>> value = new ArrayList<HashMap<String, Object>>();

		if (key.equals(""))
			return value;

		try {
			if (jO.has(key)) {
				JSONArray jA = jO.getJSONArray(key);
				if (jA != null && jA.length() != 0)
					for (int i = 0; i < jA.length(); i++) {
						JSONObject jO_item = jA.getJSONObject(i);
						value.add(getHashMap1(jO_item, STR_field));
					}
			}
		} catch (JSONException e) {
			Log.v("gyygyygyy------>" + key, "gyygyygyy------>CU_JSONResolveException" + e.toString());
			try {
				JSONObject jsonobject = jO.getJSONObject(key);
				value.add(getHashMap1(jsonobject, STR_field));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return value;
	}

	/**
	 * ��ȡJSONObject��key��2��LIST���͵�value
	 * 
	 * @param jO
	 * @param key
	 * @param STR1_field
	 * @param aL_LIST1_field
	 * @param aLL_STR2_field
	 * @return List_value
	 */
	private static ArrayList<HashMap<String, Object>> getLIST2(JSONObject jO, String key, String STR1_field[], ArrayList<String[]> aL_LIST1_field, ArrayList<ArrayList<String[]>> aLL_STR2_field) {

		ArrayList<HashMap<String, Object>> value = new ArrayList<HashMap<String, Object>>();

		if (key.equals(""))
			return value;

		try {
			if (jO.has(key)) {
				JSONArray jA = jO.getJSONArray(key);
				if (jA != null && jA.length() != 0)
					for (int i = 0; i < jA.length(); i++) {
						JSONObject jO_item = jA.getJSONObject(i);
						value.add(getHashMap2(jO_item, STR1_field, aL_LIST1_field.get(0), aLL_STR2_field.get(0)));
					}
			}
		} catch (JSONException e) {
			Log.v("gyygyygyy------>" + key, "gyygyygyy------>CU_JSONResolveException" + e.toString());
		}

		return value;
	}

	/**
	 * ����������1��JSON����
	 * 
	 * @param jsonStr
	 *            δ������JSON�ַ���
	 * @param STR1_field
	 *            ��һ��String���͵�key����
	 * @return HashMap
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 * @param �� String STR1_field[] =
	 *        {"field0","field1","field2","field3","field4"};
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 */

	public static HashMap<String, Object> parseHashMap1(String jsonStr, String STR1_field[]) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			if (jsonStr == null)
				return hashMap;

			if (STR1_field == null)
				return hashMap;

			JSONObject jO0 = new JSONObject(jsonStr);
			if (jO0.has("success"))
				if (!jO0.getBoolean("success"))
					return hashMap;

			if (STR1_field != null && STR1_field.length != 0)
				for (int i = 0; i < STR1_field.length; i++)
					hashMap.put(STR1_field[i], getSTR(jO0, STR1_field[i]));

		} catch (JSONException e) {
			Log.v("gyygyygyygyy------>", "gyygyygyygyy3------>CU_JSONResolveException" + e.toString());
		}
		return hashMap;
	}

	/**
	 * ����������2��JSON����LIST1_field��length�����aL_STR2_field��size���
	 * 
	 * @param jsonStr
	 *            δ������JSON�ַ���
	 * @param STR1_field
	 *            ��һ��String���͵�key����
	 * @param LIST1_field
	 *            ��һ��List���͵�key����
	 * @param aL_STR2_field
	 *            �ڶ���String���͵�key�б�(�б���ΪString[]:һ��LIST1_field��Ӧ��String���͵�key����)
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 * @param //һ��5��String2��list,2��ֱ�Ϊ3��String��4��String(��������໥��Ӧ)
	 * @param �� String STR1_field[] =
	 *        {"str1_0","str1_1","str1_2","str1_3","str1_4"};
	 * @param �� String LIST1_field[] = {"list1_0","list1_1"};
	 * @param �� ArrayList$String[]% aL_STR2_field = new
	 *        ArrayList$String[]%();//$��%��������
	 * @param �� String STR2_field0[] = { "str2_0_0", "str2_0_1", "str2_0_2"};
	 * @param �� String STR2_field1[] = { "str2_1_0", "str2_1_1", "str2_1_2",
	 *        "str2_1_3"};
	 * @param �� aL_STR2_field.add(STR2_field0);
	 * @param �� aL_STR2_field.add(STR2_field1);
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 * @return HashMap
	 */
	public static HashMap<String, Object> parseHashMap2(String jsonStr, String STR1_field[], String LIST1_field[], ArrayList<String[]> aL_STR2_field) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {
			if (jsonStr == null)
				return hashMap;

			if (STR1_field == null && LIST1_field == null)
				return hashMap;

			JSONObject jO0 = new JSONObject(jsonStr);
//			if (jO0.has("success"))
//				if (!jO0.getBoolean("success"))
//					return hashMap;

			if (STR1_field != null && STR1_field.length != 0)
				for (int i = 0; i < STR1_field.length; i++)
					hashMap.put(STR1_field[i], getSTR(jO0, STR1_field[i]));

			if (LIST1_field != null && LIST1_field.length != 0)
				if (aL_STR2_field != null && aL_STR2_field.size() == LIST1_field.length)
					for (int i = 0; i < LIST1_field.length; i++)
						if (aL_STR2_field.get(i) != null && aL_STR2_field.get(i).length != 0)
							hashMap.put(LIST1_field[i], getLIST1(jO0, LIST1_field[i], aL_STR2_field.get(i)));

		} catch (JSONException e) {
			Log.v("gyygyygyygyy------>", "gyygyygyygyy4------>CU_JSONResolveException" + e.toString());
		}
		return hashMap;
	}

	/**
	 * ����������3��JSON���󣺽���ʹ��
	 * 
	 * @param jsonStr
	 *            δ������JSON�ַ���
	 * @param STR1_field
	 *            ��һ��String���͵�key����
	 * @param LIST1_field
	 *            ��һ��List���͵�key����
	 * @param aL_STR2_field
	 *            �ڶ���String���͵�key�б�(�б���ΪString[]:һ��LIST1_field��Ӧ��String���͵�key����)
	 * @param aL_LIST2_field
	 *            �ڶ���List����key�б�,ͬaL_STR2_field
	 * @param aL_STR3_field
	 *            ������String���͵�key�б����б�(ArrayList$ArrayList$String[]%%)//$��%��������
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 * @param 
	 *        //һ��2��String1��list,2��ֱ�Ϊ4��String��2��List,3��ֱ�Ϊ(�ֱ�Ϊ6��String��5��String)
	 *        ��()��()...
	 * @param �� String STR1_field[] = {"str1_0","str1_1"};
	 * @param �� String LIST1_field[] = {"list1_0"};
	 * @param �� ArrayList$String[]% aL_STR2_field = new
	 *        ArrayList$String[]%();//$��%��������
	 * @param �� String STR2_field0[] = { "str2_0_0", "str2_0_1",
	 *        "str2_0_2","str2_0_3"};
	 * @param �� aL_STR2_field.add(STR2_field0);
	 * @param �� ArrayList$String[]% aL_LIST2_field = new
	 *        ArrayList$String[]%();//$��%��������
	 * @param �� String LIST2_field0[] = { "list2_0_0", "list2_0_1"};
	 * @param �� aL_LIST2_field.add(LIST2_field0);
	 * @param �� ArrayList$ArrayList$String[]%% aLL_STR3_field = new
	 *        ArrayList$ArrayList$String[]%%();//$��%��������
	 * @param �� ArrayList$String[]% aL_STR3_0_field0 = new
	 *        ArrayList$String[]%();//$��%��������
	 * @param �� String STR3_0_field0[] = { "str3_0_0_0", "str3_0_0_1",
	 *        "str3_0_0_2", "str3_0_0_3", "str3_0_0_4", "str3_0_0_5"};
	 * @param �� aL_STR3_0_field0.add(STR3_0_field0);
	 * @param �� ArrayList$String[]% aL_STR3_1_field0 = new
	 *        ArrayList$String[]%();//$��%��������
	 * @param �� String STR3_1_field0[] = { "str3_1_0_0", "str3_1_0_1",
	 *        "str3_1_0_2", "str3_1_0_3", "str3_1_0_4"};
	 * @param �� aL_STR3_1_field0.add(STR3_1_field0);
	 * @param �� aLL_STR3_field.add(aL_STR3_0_field0);
	 * @param �� aLL_STR3_field.add(aL_STR3_1_field0);
	 * @param �����Ǵ���ʾ��
	 *            ������������������������������������������������������������
	 * @return HashMap
	 */
	public static HashMap<String, Object> parseHashMap3(String jsonStr, String STR1_field[], String LIST1_field[], ArrayList<String[]> aL_STR2_field, ArrayList<String[]> aL_LIST2_field, ArrayList<ArrayList<String[]>> aLL_STR3_field) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {
			if (jsonStr == null)
				return hashMap;

			if (STR1_field == null && LIST1_field == null)
				return hashMap;

			JSONObject jO0 = new JSONObject(jsonStr);
			if (jO0.has("success"))
				if (!jO0.getBoolean("success"))
					return hashMap;

			if (STR1_field != null && STR1_field.length != 0)
				for (int i = 0; i < STR1_field.length; i++)
					hashMap.put(STR1_field[i], getSTR(jO0, STR1_field[i]));

			if (LIST1_field != null && LIST1_field.length != 0)
				if (aL_STR2_field != null && aL_STR2_field.size() == LIST1_field.length)
					for (int i = 0; i < LIST1_field.length; i++)
						if (aL_STR2_field.get(i) != null && aL_STR2_field.get(i).length != 0)
							hashMap.put(LIST1_field[i], getLIST2(jO0, LIST1_field[i], aL_STR2_field.get(i), aL_LIST2_field, aLL_STR3_field));

		} catch (JSONException e) {
			Log.v("gyygyygyygyy------>", "gyygyygyygyy5------>CU_JSONResolveException" + e.toString());
		}
		return hashMap;
	}
}