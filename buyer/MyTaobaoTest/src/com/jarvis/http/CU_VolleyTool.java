package com.jarvis.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class CU_VolleyTool {
	private static CU_VolleyTool mInstance = null;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private CU_VolleyTool(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue, new CU_VolleyBitmapCache());
	}

	public static CU_VolleyTool getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new CU_VolleyTool(context);
		}
		return mInstance;
	}

	public RequestQueue getmRequestQueue() {
		return mRequestQueue;
	}

	public ImageLoader getmImageLoader() {
		return mImageLoader;
	}

	public void release() {
		this.mImageLoader = null;
		this.mRequestQueue = null;
		mInstance = null;
	}
}
